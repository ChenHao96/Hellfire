package com.github.chenhao96.controller;

import com.github.chenhao96.config.SpringWebSecurityConfig;
import com.github.chenhao96.controller.interceptor.VerificationDeviceInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonsPageController {

    @RequestMapping(SpringWebSecurityConfig.LOGIN_URL_VALUE)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(VerificationDeviceInterceptor.VERIFICATION_DEVICE_URL)
    public String verificationPage() {
        return "verification";
    }
}
