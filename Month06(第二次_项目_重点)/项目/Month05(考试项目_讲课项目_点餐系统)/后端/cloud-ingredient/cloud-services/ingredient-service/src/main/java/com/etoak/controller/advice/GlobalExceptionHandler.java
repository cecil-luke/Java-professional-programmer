package com.etoak.controller.advice;

import com.etoak.common.exception.ParamException;
import com.etoak.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义参数异常
     */
    @ExceptionHandler(ParamException.class)
    public Result handle(ParamException e) {
        log.error(e.getMessage(), e);
        return Result.failed(e.getMessage());
    }

    /**
     * 处理其它异常
     */
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        log.error(e.getMessage(), e);
        return Result.failed();
    }
}
