package com.github.chenhao96.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.config.SpringWebSecurityConfig;
import com.github.chenhao96.utils.ErrorHandlerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SecurityAuthenticationHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

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
        log.warn("session:{}, AuthenticationException:{}", request.getSession().getId(), exception.getClass());

        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setMessage(message);
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorHandlerResponse.setUrl(SpringWebSecurityConfig.LOGIN_URL_VALUE);
        errorHandlerResponse.doResponse();
    }
}
