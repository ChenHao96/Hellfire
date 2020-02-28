package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.config.SpringWebSecurityConfig;
import com.github.chenhao96.entity.vo.UsersLogin;
import com.github.chenhao96.service.VerificationSendCodeService;
import com.github.chenhao96.utils.DateTimeUtil;
import com.github.chenhao96.utils.RandomCodeClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class VerificationDeviceInterceptor implements HandlerInterceptor {

    private static final String IS_VERIFICATION_PARAM_KEY = "is_v";
    private static final String VERIFICATION_TYPE_PARAM_KEY = "v_type";
    private static final String VERIFICATION_CODE_PARAM_KEY = "v_code";
    private static final String VERIFICATION_DEVICE_COOKIE_PREFIX = "|verification|device|";
    private static final String VERIFICATION_DEVICE_COOKIE_KEY = "verification_device_cookie";
    private static final String VERIFICATION_DEVICE_VALUE_SESSION_KEY = "verification_device_value";
    private static final String VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY = "verification_device_life_time";

    private static final RandomCodeClass randomCodeClass = RandomCodeClass.getInstance();

    public static final String VERIFICATION_DEVICE_URL = "/verification";

    @Value("${verification.device.codeLength}")
    private int codeLength;

    @Value("${verification.device.lifeSeconds}")
    private int deviceLifeSeconds;

    @Value("${verification.device.codeLifeMilliseconds}")
    private long codeLifeMilliseconds;

    @Resource
    private VerificationSendCodeService verificationSendCodeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (checkEnableDevice(request, response)) return true;
        Map<String, String[]> params = request.getParameterMap();
        //判断验证还是拦截
        if (params.containsKey(IS_VERIFICATION_PARAM_KEY)) {//验证
            return checkVerificationCode(request, response);
        } else {//拦截-发送验证码
            if (!params.containsKey(VERIFICATION_TYPE_PARAM_KEY)) {//拦截
                jump2VerificationPage(request, response);
            } else {//发送验证码
                sendVerificationCode(request);
            }
        }
        return false;
    }

    private boolean checkVerificationCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (checkEnableCode(request, response)) return true;
        //验证码不匹配或是验证码失效，重新登录
        request.getRequestDispatcher(SpringWebSecurityConfig.LOGIN_URL_VALUE).forward(request, response);
        SecurityContextHolder.clearContext();
        return false;
    }

    private void jump2VerificationPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将请求参数带到验证页面
        request.setAttribute("method",request.getMethod());
        request.setAttribute("params", request.getParameterMap());
        request.setAttribute("requestUrl", request.getRequestURI());
        //验证码的长度
        request.setAttribute("codeLength", codeLength);
        //验证码的有效时间
        request.setAttribute("codeLifeTimeStr", DateTimeUtil.milliSecond2Str(codeLifeMilliseconds));
        request.getRequestDispatcher(VERIFICATION_DEVICE_URL).forward(request, response);
    }

    private void sendVerificationCode(HttpServletRequest request) {

        //获取登录用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersLogin usersLogin = (UsersLogin) authentication.getPrincipal();
        String toAddress = usersLogin.getPhoneNumber();

        //判断验证码的发送方式
        String type = request.getParameter(VERIFICATION_TYPE_PARAM_KEY);
        if ("email".equals(type)) {
            if (StringUtils.isEmpty(usersLogin.getEmail())) {
                type = "mobile";
            } else {
                toAddress = usersLogin.getEmail();
            }
        }
        String code = refreshVerificationCode(request.getSession());
        doSendVerificationCode(type, toAddress, code);
    }

    private boolean checkEnableCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        long currentTime = System.currentTimeMillis();
        Long codeLifeTime = (Long) session.getAttribute(VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY);
        if (codeLifeTime != null && codeLifeTime > currentTime) {
            String code = (String) session.getAttribute(VERIFICATION_DEVICE_VALUE_SESSION_KEY);
            String paramCode = request.getParameter(VERIFICATION_CODE_PARAM_KEY);
            if (!StringUtils.isEmpty(code) && code.equalsIgnoreCase(paramCode)) {
                writeEnableDevice(response);
                return true;
            }
            request.setAttribute("message", "验证码不正确，请重新登录！");
        }
        return false;
    }

    private String refreshVerificationCode(HttpSession session) {
        //判断验证码的有效时间，过期重新刷新
        long currentTime = System.currentTimeMillis();
        Long codeLifeTime = (Long) session.getAttribute(VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY);
        String code = (String) session.getAttribute(VERIFICATION_DEVICE_VALUE_SESSION_KEY);
        if (code == null || codeLifeTime == null || codeLifeTime < currentTime) {
            code = randomCodeClass.createCode(codeLength);
            codeLifeTime = currentTime + codeLifeMilliseconds;
            session.setAttribute(VERIFICATION_DEVICE_VALUE_SESSION_KEY, code);
            session.setAttribute(VERIFICATION_DEVICE_LIFE_TIME_SESSION_KEY, codeLifeTime);
        }
        return code;
    }

    private boolean checkEnableDevice(HttpServletRequest request, HttpServletResponse response) {
        //获取登录用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails usersLogin = (UserDetails) authentication.getPrincipal();
            if (usersLogin == null) return false;
            //cookies验证设备有效
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if (!StringUtils.isEmpty(name)) {
                        if (name.startsWith(VERIFICATION_DEVICE_COOKIE_PREFIX)) {
                            if (name.endsWith(usersLogin.getUsername())) {
                                String valueMd5 = String.format("%s%s", name, VERIFICATION_DEVICE_COOKIE_KEY);
                                valueMd5 = DigestUtils.md5DigestAsHex(valueMd5.getBytes());
                                if (valueMd5.equalsIgnoreCase(cookie.getValue())) return true;
                                //校验到不正常的cookie时清除
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void writeEnableDevice(HttpServletResponse response) {
        //获取登录用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails usersLogin = (UserDetails) authentication.getPrincipal();
        if (usersLogin == null) return;
        //写cookies设备有效
        StringBuilder cookieNameBuilder = new StringBuilder(VERIFICATION_DEVICE_COOKIE_PREFIX);
        String cookieName = cookieNameBuilder.append(usersLogin.getUsername()).toString();
        cookieNameBuilder.append(VERIFICATION_DEVICE_COOKIE_KEY);
        String cookiesValue = DigestUtils.md5DigestAsHex(cookieNameBuilder.toString().getBytes());
        Cookie enableDevice = new Cookie(cookieName, cookiesValue);
        enableDevice.setMaxAge(deviceLifeSeconds);
        enableDevice.setPath("/");
        response.addCookie(enableDevice);
    }

    private void doSendVerificationCode(String type, String toAddress, String code) {
        if ("email".equals(type)) {
            verificationSendCodeService.deviceEmail(toAddress, code);
        } else {
            verificationSendCodeService.deviceMobilePhone(toAddress, code);
        }
    }
}
