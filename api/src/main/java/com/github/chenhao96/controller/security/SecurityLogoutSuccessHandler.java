package com.github.chenhao96.controller.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.utils.ErrorHandlerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setMsg("安全退出。");
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setCode(HttpStatus.OK.value());
        errorHandlerResponse.doResponse();
    }
}
