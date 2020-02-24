package com.github.chenhao96.controller;

import com.github.chenhao96.config.SpringWebSecurityConfig;
import com.github.chenhao96.controller.interceptor.VerificationDeviceInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonsController {

    @RequestMapping(SpringWebSecurityConfig.LOGIN_URL_VALUE)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(VerificationDeviceInterceptor.VERIFICATION_DEVICE_URL)
    public String verificationPage() {
        return "verification";
    }

    @ResponseBody
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('commons:test')")
    public String testPage() {
        return "test";
    }
}
