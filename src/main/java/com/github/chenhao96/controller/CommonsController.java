package com.github.chenhao96.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonsController {

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('commons:test')")
    public String testPage() {
        return "test";
    }
}
