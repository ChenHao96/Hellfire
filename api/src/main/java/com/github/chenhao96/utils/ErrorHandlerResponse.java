package com.github.chenhao96.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chenhao96.entity.vo.BaseResult;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Accessors(chain = true)
public class ErrorHandlerResponse extends BaseResult<Object> {

    @JsonIgnore
    private HttpServletRequest request;

    @JsonIgnore
    private HttpServletResponse response;

    @JsonIgnore
    private ObjectMapper objectMapper;

    public void doResponse() throws IOException {
        response.setCharacterEncoding(CommonsUtil.DEFAULT_ENCODING);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(this));
        response.getWriter().flush();
    }
}
