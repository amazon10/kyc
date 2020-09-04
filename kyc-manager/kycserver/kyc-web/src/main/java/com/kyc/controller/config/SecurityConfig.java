package com.kyc.controller.config;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyc.model.Builder;
import com.kyc.model.RespBean;
import com.kyc.model.VariableConstants;
import com.kyc.model.system.LogRecord;
import com.kyc.model.system.User;
import com.kyc.service.system.LogRecordService;
import com.kyc.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    LogRecordService logRecordService;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Autowired
    CustomUrlDecisionManager customUrlDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico");
    }

    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    User user = (User) authentication.getPrincipal();
                    user.setPassword(null);
                    RespBean ok = RespBean.ok("登录成功!", user);
                    String s = new ObjectMapper().writeValueAsString(ok);
                    out.write(s);
                    out.flush();
                    out.close();

                    // 写入日志记录
                    LogRecord logRecord = Builder.of(LogRecord::new)
                            .with(LogRecord::setUsername, user.getUsername())
                            .with(LogRecord::setLoginId, SystemUtil.getIpAddress(request))
                            .with(LogRecord::setLoginTime, DateUtil.format(LocalDateTime.now(), VariableConstants.DATETIME_FORMATTER))
                            .with(LogRecord::setLogContent, "用户登录系统成功")
                            .build();
                    logRecordService.addLogRecord(logRecord);
                }
        );

        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            RespBean respBean = RespBean.error(exception.getMessage());
            if (exception instanceof LockedException) {
                respBean.setMsg("账户被锁定，请联系管理员!");
            } else if (exception instanceof CredentialsExpiredException) {
                respBean.setMsg("密码过期，请联系管理员!");
            } else if (exception instanceof AccountExpiredException) {
                respBean.setMsg("账户过期，请联系管理员!");
            } else if (exception instanceof DisabledException) {
                respBean.setMsg("账户被禁用，请联系管理员!");
            } else if (exception instanceof BadCredentialsException) {
                respBean.setMsg("用户名或者密码输入错误，请重新输入!");
            }
            out.write(new ObjectMapper().writeValueAsString(respBean));
            out.flush();
            out.close();
        });

        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/com/kyc/doLogin");
        ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        sessionStrategy.setMaximumSessions(1);
        loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
        return loginFilter;
    }

    @Bean
    SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功")));
                    out.flush();
                    out.close();

                    LogRecord logRecord = Builder.of(LogRecord::new)
                            .with(LogRecord::setUsername, ((User)authentication.getPrincipal()).getUsername())
                            .with(LogRecord::setLoginId, SystemUtil.getIpAddress(request))
                            .with(LogRecord::setLoginTime, DateUtil.format(LocalDateTime.now(), VariableConstants.DATETIME_FORMATTER))
                            .with(LogRecord::setLogContent, "用户退出系统成功")
                            .build();
                    logRecordService.addLogRecord(logRecord);
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                // 没有认证时，在这里处理结果，不再重定向
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(401);
                    PrintWriter out = response.getWriter();
                    RespBean respBean = RespBean.error("访问失败！");
                    if (authException instanceof InsufficientAuthenticationException) {
                        respBean.setMsg("请求失败，请联系管理员！");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                });

        httpSecurity.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
            HttpServletResponse response = event.getResponse();
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(401);
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(RespBean.error("您已经在一台设备上登录，本次登录已下线！")));
            out.flush();
            out.close();
        }), ConcurrentSessionFilter.class);
        httpSecurity.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
