package com.github.chenhao96.utils.jdbc;

public class CallableParam {

    public static final int IN_PARAM = 0;
    public static final int OUT_PARAM = 1;
    public static final int IN_OUT_PARAM = 2;

    public Integer type;
    public Object value;
    public Object returnValue;
    public int isInOrOutAndInOut = IN_PARAM;

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