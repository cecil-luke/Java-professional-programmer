package com.etoak.controller.handler;

import com.etoak.common.ResultVO;
import com.etoak.exception.StorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(StorageException.class)
    public ResultVO handle(StorageException e) {
        log.error(e.getMessage(), e);
        return ResultVO.failed(e.getMessage());
    }

}
