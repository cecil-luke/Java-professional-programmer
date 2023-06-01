package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cloud_storage")
public class Storage {

  @TableId(type = IdType.AUTO)
  private Integer id;

  private String productCode;

  private Integer count;
}
