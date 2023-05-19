package com.etoak.common.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginVO {

  @NotBlank(message = "不能为空")
  private String username;

  @NotBlank(message = "不能为空")
  private String password;

  @NotBlank(message = "不能为空")
  private String uuid;

  @NotBlank(message = "不能为空")
  private String code;


}
