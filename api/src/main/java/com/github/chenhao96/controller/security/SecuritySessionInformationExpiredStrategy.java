package com.github.chenhao96.controller.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.utils.ErrorHandlerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

public class SecuritySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setRequest(event.getRequest());
        errorHandlerResponse.setResponse(event.getResponse());
        errorHandlerResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorHandlerResponse.setMessage("该账号已在其他设备登录，如有异常及时联系管理员!");
        errorHandlerResponse.doResponse();
        SecurityContextHolder.clearContext();
    }
}
