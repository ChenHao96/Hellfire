package com.github.chenhao96.utils.jdbc;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CallableParam {

    public static final int IN_PARAM = 0;
    public static final int OUT_PARAM = 1;
    public static final int IN_OUT_PARAM = 2;

    private Integer type;
    private Object value;
    private Object returnValue;
    private int isInOrOutAndInOut = IN_PARAM;

    public CallableParam(Integer type, Object value) {
        this.type = type;
        this.value = value;
    }

    public CallableParam(Integer type, Object value, int isInOrOutAndInOut) {
        this.type = type;
        this.value = value;
        this.isInOrOutAndInOut = isInOrOutAndInOut;
    }
}