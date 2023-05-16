package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * t_dict对应的实体类
 *
 * @author et2301
 * @since 2023-05-15
 */
@Data
@TableName("t_dict")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dict implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String type;

  private String name;

  private String value;

  private Integer sort;

}
