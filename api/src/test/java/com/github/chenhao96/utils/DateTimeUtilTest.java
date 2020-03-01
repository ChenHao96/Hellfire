package com.github.chenhao96.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class DateTimeUtilTest {

    @Test
    public void testTime2Str() {
        long milliSecond = 86401000L;
        String str = DateTimeUtil.milliSecond2Str(milliSecond);
        log.info("milliSecond:{} milliSecond2Str:{}", milliSecond, str);
        Assert.assertEquals("验证失败"," 1天 1秒",str);
    }
}
