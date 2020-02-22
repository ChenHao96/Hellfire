package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.entity.po.UserStatusEnum;
import com.github.chenhao96.entity.vo.UsersLogin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        SecurityContext securityContext = (SecurityContext) httpSession.getAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            UsersLogin login = (UsersLogin) authentication.getPrincipal();
            if (UserStatusEnum.ENABLE.equals(login.getStatus())) {
                return true;
            }
        }
        request.setAttribute("isError", true);
        request.setAttribute("message", "登录超时, 请重新登录!");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }
}
