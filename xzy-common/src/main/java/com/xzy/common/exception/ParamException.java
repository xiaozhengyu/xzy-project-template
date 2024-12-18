package com.xzy.common.exception;

/**
 * @author xzy.xiao
 * @since 2024/12/17  18:26
 */

public class ParamException extends RuntimeException {

    private static final long serialVersionUID = 5492642528230689911L;

    public ParamException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}