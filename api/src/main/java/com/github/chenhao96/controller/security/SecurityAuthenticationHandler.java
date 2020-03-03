package com.github.chenhao96.controller.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.utils.ErrorHandlerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class SecurityAuthenticationHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String message = "账号不可用!";
        if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误!";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "账号已过期，请联系管理员!";
        } else if (exception instanceof LockedException) {
            message = "账号已锁定!";
        } else if (exception instanceof SessionAuthenticationException) {
            message = "该账号已在其他设备登录，如有异常及时联系管理员!";
        }
        //TODO:多次登录失败
        //1.删除设备验证
        //2.短时间拒绝任何请求
        //3.封禁IP
        log.warn("session:{}, AuthenticationException:{}", request.getSession().getId(), exception.getClass());
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setMessage(message);
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorHandlerResponse.doResponse();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication auth) throws IOException {

        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setMessage("登录成功!");
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setCode(HttpStatus.OK.value());
        errorHandlerResponse.doResponse();
    }
}
