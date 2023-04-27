package com.w83ll43.openapi.common;

/**
 * 基于 ThreadLocal 封装工具类
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置 ID
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取 ID
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
