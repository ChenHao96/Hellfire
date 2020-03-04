package com.github.chenhao96.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class BaseResult<D> implements Serializable {

    @ApiModelProperty(value = "请求响应码", required = true, example = "200")
    private int code;

    @ApiModelProperty(value = "请求响应信息", required = true)
    private String msg;

    @ApiModelProperty(value = "请求响应内容")
    private D data;

    public BaseResult() {
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, String msg, D data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
