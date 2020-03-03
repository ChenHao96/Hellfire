package com.github.chenhao96.controller;

import com.github.chenhao96.entity.vo.AuthUserDetail;
import com.github.chenhao96.entity.vo.LoginUserVo;
import com.github.chenhao96.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommonsController extends AbstractController {

    @Resource
    private UserService userService;

    @GetMapping("/getInfo")
    public LoginUserVo getLoginUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof AuthUserDetail) {
            AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal();
            return authUserDetail.getInfo();
        }
        return null;
    }
}
