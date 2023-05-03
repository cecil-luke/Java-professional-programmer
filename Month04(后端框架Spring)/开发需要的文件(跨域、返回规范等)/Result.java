package com.etoak.rst.utils;

public enum Result {
    SUCCESS(200," invoke success"),//成功
    ERROR(20000,"invoke error"),//失败
    LOGIN_SUCCESS(20001,"login success"),//登录成功
    LOGIN_ERROR(20002,"username or pwd error"),//登陆失败
    TIMEOUT(20003,"request timeout "),//超时
    CHECKCODE_ERROR(20004,"checkcode invalid"),
    TOOMANY(20006,"too many.."),
    PARAMETER_NOT_ENOUGH(20005,"parameters not enough" );//验证码错误
    int code;
    String msg;
    private Result(int code,String msg){ this.code = code;this.msg = msg; }

    public static JsonResponse suc(Object data){
        return new JsonResponse(SUCCESS.code,SUCCESS.msg,data);
    }
    public static JsonResponse error(Object data){
        return new JsonResponse(ERROR.code,ERROR.msg,data);
    }
    public static JsonResponse loginSuccess(Object data){
        return new JsonResponse(LOGIN_SUCCESS.code,LOGIN_SUCCESS.msg,data);
    }
    public static JsonResponse longinError(Object data){
        return new JsonResponse(LOGIN_ERROR.code,LOGIN_ERROR.msg,data);
    }

    public static JsonResponse paramsNotEnough(String data) {
        return new JsonResponse(PARAMETER_NOT_ENOUGH.code,PARAMETER_NOT_ENOUGH.msg,data);
    }

    public static JsonResponse timeout(String data) {
        return new JsonResponse(TIMEOUT.code,TIMEOUT.msg,data);
    }

    public static JsonResponse tooMany(String str) {
        return new JsonResponse(TOOMANY.code,TOOMANY.msg,str);
    }
}
