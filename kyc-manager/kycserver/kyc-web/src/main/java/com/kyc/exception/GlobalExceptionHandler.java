package com.kyc.exception;

import com.kyc.model.RespBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.concurrent.Executor;

/**
 * 异常统一处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据有关联数据，操作失败!");
        }
        return RespBean.error("数据库异常，操作失败!");
    }

    @ExceptionHandler(RuntimeException.class)
    public RespBean runtimeException(RuntimeException e) {
        return RespBean.error("程序运行期异常");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public RespBean illegalArgumenExceotion(IllegalArgumentException e) {
        return RespBean.error("参数检查错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespBean methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return RespBean.error("参数校验失败");
    }

    @ExceptionHandler(Exception.class)
    public RespBean exception(Exception e) {
        return RespBean.error("系统错误");
    }
}