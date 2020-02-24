package com.github.chenhao96.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatusEnum {
    DISABLE(0), ENABLE(1), LOCKED(2);

    @JsonValue
    @EnumValue
    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
