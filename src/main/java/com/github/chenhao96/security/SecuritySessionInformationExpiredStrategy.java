package com.github.chenhao96.security;

import com.github.chenhao96.config.SpringWebSecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SecuritySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getRequest();
        request.setAttribute("errMsg", "该账号已在其他设备登录，如有异常及时联系管理员!");
        request.getRequestDispatcher(SpringWebSecurityConfig.ERROR_URL_VALUE).forward(request, event.getResponse());
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
    }
}
