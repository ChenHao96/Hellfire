package com.github.chenhao96.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.entity.vo.BaseResult;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class ErrorHandlerResponse {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private String url;

    private int code;

    private String message;

    private ObjectMapper objectMapper;

    private Map<String, Object> data;

    public void doResponse() throws ServletException, IOException {
        String accept = request.getHeader("Accept");
        if (!StringUtils.isEmpty(accept) && accept.contains("html")) {
            if (!CollectionUtils.isEmpty(data)) {
                Set<Map.Entry<String, Object>> attrs = data.entrySet();
                for (Map.Entry<String, Object> attr : attrs) {
                    request.setAttribute(attr.getKey(), attr.getValue());
                }
            }
            request.setAttribute("errMsg", message);
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            BaseResult<Map<String, Object>> result = new BaseResult<>(code, message);
            result.setData(data);
            response.setCharacterEncoding(CommonsUtil.DEFAULT_ENCODING);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(result));
            response.getWriter().flush();
        }
    }
}
