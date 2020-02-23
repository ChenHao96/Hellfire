package com.github.chenhao96.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAuthenticationHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String message = "账号不可用!";
        if (e instanceof BadCredentialsException) {
            message = "用户名或密码错误!";
        } else if (e instanceof CredentialsExpiredException) {
            message = "账号已过期，请联系管理员!";
        } else if (e instanceof LockedException) {
            message = "账号已锁定!";
        } else if (e instanceof SessionAuthenticationException) {
            message = "该账号已在其他设备登录，如有异常及时联系管理员!";
        }
        LOGGER.warn("session:{}, AuthenticationException:{}", httpServletRequest.getSession().getId(), e.getClass());

        httpServletRequest.setAttribute("message", message);
        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
    }
}
