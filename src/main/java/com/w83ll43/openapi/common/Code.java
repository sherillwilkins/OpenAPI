package com.w83ll43.openapi.common;

/**
 * 自定义状态码
 */
public enum Code {

    SUCCESS(0, "响应成功"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN(40100, "用户未登录"),
    NO_AUTH_ERROR(40101, "用户无权限"),
    NO_SUCH_USER(40200, "用户不存在"),
    PASSWORD_ERROR(40201, "用户密码错误"),
    NO_SUCH_INTERFACE(40300, "接口不存在");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    /**
     * 构造函数
     * @param code
     * @param message
     */
    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Code 的 get 方法
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * Message 的 get 方法
     * @return
     */
    public String getMessage() {
        return message;
    }
}
