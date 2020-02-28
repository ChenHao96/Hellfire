package com.github.chenhao96.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.config.SpringWebSecurityConfig;
import com.github.chenhao96.utils.ErrorHandlerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorHandlerResponse.setUrl(SpringWebSecurityConfig.ERROR_URL_VALUE);
        errorHandlerResponse.setMessage("您的账号没有该链接的访问权限，如有异常请联系管理员处理！");
        errorHandlerResponse.doResponse();
    }
}
