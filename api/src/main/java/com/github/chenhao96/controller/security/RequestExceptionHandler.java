package com.github.chenhao96.controller.security;

import com.github.chenhao96.entity.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResult<?> handlerNotFoundException(Exception exception) {
        //TODO:多次请求无效URL
        //1.短时间拒绝任何请求
        //2.封禁IP
        BaseResult<?> result = new BaseResult<>();
        result.setCode(HttpStatus.UNAUTHORIZED.value());
        result.setMsg("您的账号没有该链接(方式)的访问权限，如有异常请联系管理员处理！");
        log.warn("handlerNotFoundException:{}", exception.getMessage());
        return result;
    }
}
