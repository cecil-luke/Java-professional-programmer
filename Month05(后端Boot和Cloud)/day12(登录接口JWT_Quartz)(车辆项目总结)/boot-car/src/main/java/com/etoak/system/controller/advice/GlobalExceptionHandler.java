package com.etoak.system.controller.advice;

import com.etoak.common.exception.ParamException;
import com.etoak.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * 处理自定义参数异常
   */
  @ExceptionHandler(ParamException.class)
  public ResultVO handle(ParamException e) {
    log.warn(e.getMessage(), e);
    return ResultVO.failed(e.getMessage());
  }

  /**
   * 处理其它异常
   */
  @ExceptionHandler(Exception.class)
  public ResultVO handle(Exception e) {
    log.error(e.getMessage(), e);
    return ResultVO.failed();
  }

}
