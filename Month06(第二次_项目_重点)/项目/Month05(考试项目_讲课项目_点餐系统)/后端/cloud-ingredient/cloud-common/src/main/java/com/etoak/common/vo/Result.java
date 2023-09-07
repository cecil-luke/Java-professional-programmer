package com.etoak.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * REST接口返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MESSAGE = "OK";

    public static final int ERROR_CODE = 500;

    public static final String ERROR_MESSAGE = "系统异常！";

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应体
     */
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    /**
     * 返回500、系统异常
     */
    public static <T> Result<T> failed() {
        return failed(ERROR_MESSAGE);
    }

    public static <T> Result<T> failed(String message) {
        return failed(ERROR_CODE, message);
    }

    public static <T> Result<T> failed(int code, String message) {
        return new Result<>(code, message, null);
    }

}

