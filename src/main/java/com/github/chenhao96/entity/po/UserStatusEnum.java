package com.github.chenhao96.entity.po;

public enum UserStatusEnum {
    DISABLE(0), ENABLE(1), LOCKED(2);

    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
