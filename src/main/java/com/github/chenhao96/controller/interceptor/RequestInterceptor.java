package com.github.chenhao96.controller.interceptor;

import com.github.chenhao96.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    public static final String SERVLET_PATH_PARAMETER_NAME = "RequestInterceptor_servlet_path_key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = getIp(request);
        String requestUrl = getServletUrl(request);
        request.setAttribute(AbstractController.CLIENT_IP_KEY, ip);
        String param = catalinaMap2String(request.getParameterMap());
        request.setAttribute(SERVLET_PATH_PARAMETER_NAME, requestUrl);
        log.info("request address:{}:{},requestUrl:{},param:{}", ip, requestUrl, param);
        return true;
    }

    public static String getServletUrl(HttpServletRequest request) {
        String requestUrl = request.getServletPath();
        if (StringUtils.isEmpty(requestUrl)) {
            requestUrl = request.getRequestURI();
            String contextPath = request.getContextPath();
            if (contextPath != null && contextPath.length() > 0) {
                requestUrl = requestUrl.substring(contextPath.length());
            }
        }
        return requestUrl;
    }

    private String catalinaMap2String(Map<String, String[]> map) {
        Set<String> keys = map.keySet();
        StringBuilder msg = new StringBuilder("{");
        if (keys.size() > 0) {

            for (String key : keys) {
                msg.append(",");
                msg.append(key);
                msg.append(":");
                msg.append(Arrays.toString(map.get(key)));
            }

            if (msg.length() > 0) {
                msg.delete(1, 2);
            }
        }
        msg.append("}");
        return msg.toString();
    }

    public static String getIp(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("proxy-client-ip");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("wl-proxy-client-ip");
        }

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isEmpty(ip) || "0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)) {
            ip = "127.0.0.1";
        }

        if (ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }

        return ip;
    }
}
