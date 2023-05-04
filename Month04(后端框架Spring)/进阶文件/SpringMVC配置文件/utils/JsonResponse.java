package com.etoak.rst.utils;

import lombok.Data;

@Data
public class JsonResponse {
    private int code;
    private String msg;
    private Object data;

    public JsonResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
