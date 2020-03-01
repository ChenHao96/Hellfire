package com.github.chenhao96.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.entity.vo.BaseResult;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ErrorHandlerResponse {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private int code;

    private String message;

    private ObjectMapper objectMapper;

    private Map<String, Object> data;

    public void doResponse() throws IOException {
        BaseResult<Map<String, Object>> result = new BaseResult<>(code, message);
        result.setData(data);
        response.setCharacterEncoding(CommonsUtil.DEFAULT_ENCODING);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }
}
