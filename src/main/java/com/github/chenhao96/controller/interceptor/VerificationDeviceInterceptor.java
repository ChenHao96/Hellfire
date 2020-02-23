package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.utils.RandomCodeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerificationDeviceInterceptor implements HandlerInterceptor {

    private static final String IS_VERIFICATION_PARAM_KEY = "is_v";
    private static final String VERIFICATION_TYPE_PARAM_KEY = "v_type";
    private static final String VERIFICATION_CODE_PARAM_KEY = "v_code";
    private static final String VERIFICATION_DEVICE_VALUE_SESSION_KEY = "verification_device_value";
    private static final String VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY = "verification_device_life_time";

    public static final String VERIFICATION_DEVICE_URL = "/verification";

    private static final RandomCodeClass randomCodeClass = RandomCodeClass.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationDeviceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //TODO:获取cookies验证设备有效

        //获取请求参数
        HttpSession session = request.getSession();
        long currentTime = System.currentTimeMillis();
        Map<String, String[]> params = request.getParameterMap();
        Long codeLifeTime = (Long) session.getAttribute(VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY);

        //判断验证还是拦截
        if (params.containsKey(IS_VERIFICATION_PARAM_KEY)) {//验证
            if (codeLifeTime != null && codeLifeTime > currentTime) {
                String code = (String) session.getAttribute(VERIFICATION_DEVICE_VALUE_SESSION_KEY);
                String paramCode = request.getParameter(VERIFICATION_CODE_PARAM_KEY);
                if (!StringUtils.isEmpty(code) && code.equalsIgnoreCase(paramCode)) {
                    //TODO:写cookies设备有效
                    return true;
                }
                request.setAttribute("message", "验证码不正确，请重新登录！");
            }

            request.getRequestDispatcher("/login").forward(request, response);
            session.removeAttribute(VERIFICATION_DEVICE_VALUE_SESSION_KEY);
            session.removeAttribute(VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY);
            SecurityContextHolder.clearContext();
        } else {//拦截

            final int codeLength = 6;
            final int codeLifeValue = 10;//分钟

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UsersLogin usersLogin = (UsersLogin) authentication.getPrincipal();
            String type = StringUtils.isEmpty(usersLogin.getEmail()) ? "mobile" : "email";
            if (!params.containsKey(VERIFICATION_TYPE_PARAM_KEY)) {
                //拦截-将请求参数带到验证页面
                request.setAttribute("params", params);
                request.setAttribute("codeLength", codeLength);
                request.setAttribute("method", request.getMethod());
                request.setAttribute("requestUrl", request.getRequestURI());
                request.setAttribute("codeLifeTime", TimeUnit.MINUTES.toMinutes(codeLifeValue));
                request.getRequestDispatcher(VERIFICATION_DEVICE_URL).forward(request, response);
            } else {
                type = request.getParameter(VERIFICATION_TYPE_PARAM_KEY);
            }

            if (codeLifeTime == null || codeLifeTime < currentTime) {
                String code = randomCodeClass.createCode(codeLength);
                session.setAttribute(VERIFICATION_DEVICE_VALUE_SESSION_KEY, code);
                codeLifeTime = currentTime + TimeUnit.MINUTES.toMillis(codeLifeValue);
                session.setAttribute(VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY, codeLifeTime);
                LOGGER.info("verification code:{}", code);
            }

            //TODO:验证码发送
            if ("email".equalsIgnoreCase(type)) {

            } else {

            }
        }
        return false;
    }
}
