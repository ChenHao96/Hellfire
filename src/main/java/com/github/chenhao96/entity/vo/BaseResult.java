package com.github.chenhao96.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResult {

    private int code;

    private String msg;

    public BaseResult() {
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
