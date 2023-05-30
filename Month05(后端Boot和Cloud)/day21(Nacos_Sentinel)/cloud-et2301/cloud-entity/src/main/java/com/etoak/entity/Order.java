package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cloud_order")
public class Order {

  @TableId(type = IdType.AUTO)
  private Integer id;

  private String userId;

  private String productCode;

  private Integer count;

  private Integer money;
}
