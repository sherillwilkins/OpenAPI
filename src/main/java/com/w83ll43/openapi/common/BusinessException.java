package com.w83ll43.openapi.common;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException{

    /**
     *错误码
     */
    private final int code;

    public BusinessException( int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
