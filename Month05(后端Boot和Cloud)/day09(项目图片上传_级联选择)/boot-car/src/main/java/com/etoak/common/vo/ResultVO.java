package com.etoak.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST接口统一返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

  public static final int SUCCESS_CODE = 200;

  public static final String SUCCESS_MSG = "success";

  public static final int FAILED_CODE = 500;

  public static final String FAILED_MSG = "系统异常";

  /** 响应码 */
  private Integer code;

  /** 响应消息 */
  private String msg;

  /** 响应结果数据 */
  private T data;

  public static <T> ResultVO<T> success(T data) {
    return new ResultVO<>(SUCCESS_CODE, SUCCESS_MSG, data);
  }

  public static <T> ResultVO failed() {
    return failed(FAILED_MSG);
  }

  public static <T> ResultVO failed(String msg) {
    return failed(FAILED_CODE, msg);
  }

  public static <T> ResultVO failed(int code, String msg) {
    return new ResultVO<>(code, msg, null);
  }
}
