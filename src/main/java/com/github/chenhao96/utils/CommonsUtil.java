package com.github.chenhao96.utils;

import sun.security.action.GetPropertyAction;

import java.security.AccessController;

public class CommonsUtil {

    public static final String DEFAULT_ENCODING = AccessController.doPrivileged(
            new GetPropertyAction("file.encoding")
    );

    public static void safeClose(AutoCloseable... closeAbles) {
        if (closeAbles != null && closeAbles.length > 0) {
            for (AutoCloseable closeable : closeAbles) {
                if (closeable == null) continue;
                try {
                    closeable.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
