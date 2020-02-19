package com.github.chenhao96.utils.jdbc;

public class SqlJdbcException extends RuntimeException {
    public SqlJdbcException(String message) {
        super(message);
    }

    public SqlJdbcException(Throwable throwable) {
        super(throwable);
    }
}
