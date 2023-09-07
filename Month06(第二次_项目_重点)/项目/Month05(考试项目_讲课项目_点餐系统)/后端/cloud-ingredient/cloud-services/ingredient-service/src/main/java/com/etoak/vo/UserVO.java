package com.etoak.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录接口参数
 */
@Data
public class UserVO {

    @NotBlank(message = "必填")
    private String username;

    @NotBlank(message = "必填")
    private String password;

    @NotBlank(message = "必填")
    private String uuid;

    @NotBlank(message = "必填")
    private String code;
}
