package com.github.chenhao96.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum UserStatusEnum {
    DISABLE(0), ENABLE(1), LOCKED(2);

    private static final Map<Integer, UserStatusEnum> codeLookup;

    static {
        EnumSet<UserStatusEnum> enumSet = EnumSet.allOf(UserStatusEnum.class);
        codeLookup = new ConcurrentHashMap<>(enumSet.size());
        for (UserStatusEnum type : enumSet) codeLookup.put(type.code, type);

    }

    @JsonValue
    @EnumValue
    private final int code;

    UserStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserStatusEnum fromCode(int code) {
        UserStatusEnum data = codeLookup.get(code);
        if (data == null)
            throw new IllegalArgumentException("unknown data type code");
        return data;
    }
}
