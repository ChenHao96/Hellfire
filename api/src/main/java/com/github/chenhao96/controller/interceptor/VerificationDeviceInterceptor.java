package com.github.chenhao96.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.entity.vo.AuthUserDetail;
import com.github.chenhao96.service.VerificationSendCodeService;
import com.github.chenhao96.utils.DateTimeUtil;
import com.github.chenhao96.utils.ErrorHandlerResponse;
import com.github.chenhao96.utils.RandomCodeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
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

    @Value("${verification.device.codeLength}")
    private int codeLength;

    @Value("${verification.device.lifeSeconds}")
    private int deviceLifeSeconds;

    @Value("${verification.device.codeLifeMilliseconds}")
    private long codeLifeMilliseconds;

    @Autowired
    private ObjectMapper objectMapper;

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
                response.setStatus(HttpStatus.NO_CONTENT.value());
            }
        }
        return false;
    }

    private boolean checkVerificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (checkEnableCode(request, response)) return true;
        //验证码不匹配或是验证码失效，重新登录
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setMsg("验证码不正确，请重新登录！");
        errorHandlerResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorHandlerResponse.doResponse();
        SecurityContextHolder.clearContext();
        return false;
    }

    private void jump2VerificationPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>(2);
        //验证码的长度
        data.put("codeLength", codeLength);
        //验证码的有效时间
        data.put("codeLifeTimeStr", DateTimeUtil.milliSecond2Str(codeLifeMilliseconds));
        ErrorHandlerResponse errorHandlerResponse = new ErrorHandlerResponse();
        errorHandlerResponse.setData(data);
        errorHandlerResponse.setRequest(request);
        errorHandlerResponse.setResponse(response);
        errorHandlerResponse.setObjectMapper(objectMapper);
        errorHandlerResponse.setCode(HttpStatus.CONTINUE.value());
        errorHandlerResponse.setMsg("检查到您的使用存在风险,请填写验证码验证。");
        errorHandlerResponse.doResponse();
    }

    private void sendVerificationCode(HttpServletRequest request) {

        //获取登录用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal();
        String toAddress = authUserDetail.getPhoneNumber();

        //判断验证码的发送方式
        String type = request.getParameter(VERIFICATION_TYPE_PARAM_KEY);
        if ("email".equals(type)) {
            if (StringUtils.isEmpty(authUserDetail.getEmail())) {
                type = "mobile";
            } else {
                toAddress = authUserDetail.getEmail();
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
        enableDevice.setPath("/");
        enableDevice.setMaxAge(deviceLifeSeconds);
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
