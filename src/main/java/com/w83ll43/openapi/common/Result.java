package com.w83ll43.openapi.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果类
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map map = new HashMap<>();

    /**
     * 成功
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = object;
        return result;
    }

    /**
     * 失败
     * @param message
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.message = message;
        return result;
    }

    /**
     * 向动态数据添加数据
     * @param key
     * @param value
     * @return
     */
    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
