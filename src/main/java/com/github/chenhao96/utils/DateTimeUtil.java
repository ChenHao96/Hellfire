package com.github.chenhao96.utils;

public class DateTimeUtil {

    private static final String[] timeStr = new String[]{"天", "小时", "分钟", "秒", "毫秒"};
    private static final long[] timeLongValue = new long[]{86400000L, 3600000L, 60000L, 1000L, 1L};

    public static String milliSecond2Str(long milliSecond) {
        if (milliSecond < 0) return "";
        StringBuilder result = new StringBuilder();
        long tmp = milliSecond;
        for (int i = 0; i < timeLongValue.length; i++) {
            long value = timeLongValue[i];
            if (tmp >= value) {
                result.append(" ").append(tmp / value).append(timeStr[i]);
                tmp %= value;
                if (tmp == 0) break;
            }
        }
        return result.toString();
    }
}
