package com.github.chenhao96.utils;

import sun.security.action.GetPropertyAction;

import java.security.AccessController;

public class CommonsUtil {

    public static final String SYSTEM_CREATE_ID_FIELD = "createAt";

    public static final String SYSTEM_OPTION_ID_SESSION_KEY = "system-session-login-user-id";

    public static final int PROCESS_NUMBER = Runtime.getRuntime().availableProcessors();

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
