package com.github.chenhao96.annotation;

import com.github.chenhao96.utils.CommonsUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemAdminOption {

    String field() default CommonsUtil.SYSTEM_CREATE_ID_FIELD;

    String sessionKey() default CommonsUtil.SYSTEM_OPTION_ID_SESSION_KEY;
}
