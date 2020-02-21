package com.github.chenhao96.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String message = "账号不可用!";
        if (e instanceof BadCredentialsException) {
            message = "用户名或密码错误!";
        } else if (e instanceof LockedException) {
            message = "账号已锁定!";
        } else if (e instanceof SessionAuthenticationException) {
            message = "该账号已在其他设备登录，如有异常及时联系管理员!";
        }

        httpServletRequest.setAttribute("isError", true);
        httpServletRequest.setAttribute("message", message);
        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
    }
}
