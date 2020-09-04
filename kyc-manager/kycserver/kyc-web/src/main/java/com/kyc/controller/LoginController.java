package com.kyc.controller;

import com.kyc.model.RespBean;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log
@Controller(value = "/login")
public class LoginController {
    @GetMapping()
    public RespBean login() {
        return RespBean.error("尚未登录，请登录！");
    }
}
