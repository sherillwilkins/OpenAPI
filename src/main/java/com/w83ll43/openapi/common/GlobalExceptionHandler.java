package com.w83ll43.openapi.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     * @param e
     * @return
     */
    // @ExceptionHandler(BusinessException.class)
    @ExceptionHandler(value = BusinessException.class)
    public Result<String> exceptionHandler(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Result<String> exceptionHandler(RuntimeException e) {
        return Result.error("系统错误");
    }
}
