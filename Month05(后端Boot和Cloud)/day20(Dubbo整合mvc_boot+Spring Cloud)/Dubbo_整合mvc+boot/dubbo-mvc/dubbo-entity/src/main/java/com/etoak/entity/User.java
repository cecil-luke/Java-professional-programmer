package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_user")
public class User implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String username;

  private String password;

  private String email;

  private Integer age;

  private String createTime;

}
