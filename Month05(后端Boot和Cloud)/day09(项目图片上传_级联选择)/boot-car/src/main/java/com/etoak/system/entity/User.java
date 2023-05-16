package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {

  /** 自增id */
  @TableId(type = IdType.AUTO)
  private Long id;

  private String username;

  private String password;

  private String email;

  private Integer age;

  private Integer state;

  private Long visitCount;

  private Integer money;

  private String createTime;
}
