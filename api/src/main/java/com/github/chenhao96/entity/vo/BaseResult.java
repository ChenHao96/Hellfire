package com.github.chenhao96.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResult<D> {

    @ApiModelProperty(name = "code", value = "请求响应码", required = true, example = "200")
    private int code;

    @ApiModelProperty(name = "msg", value = "请求响应信息", required = true)
    private String msg;

    @ApiModelProperty(name = "data", value = "请求响应内容")
    private D data;

    public BaseResult() {
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
