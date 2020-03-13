package com.github.chenhao96.controller;

import com.github.chenhao96.entity.vo.AuthUserDetail;
import com.github.chenhao96.entity.vo.BaseResult;
import com.github.chenhao96.entity.vo.LoginUserVo;
import com.github.chenhao96.utils.CommonsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonsController extends AbstractController {

    @GetMapping("/getInfo")
    public BaseResult<LoginUserVo> getLoginUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof AuthUserDetail) {
            AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal();
            getSession().setAttribute(CommonsUtil.SYSTEM_OPTION_ID_SESSION_KEY, authUserDetail.getId());
            return new BaseResult<>(HttpStatus.OK.value(), "操作成功。", authUserDetail.getInfo());
        }
        return null;
    }
}
