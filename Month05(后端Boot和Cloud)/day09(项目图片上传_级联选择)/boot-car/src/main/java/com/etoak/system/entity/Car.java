package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * t_car对应的实体类
 *
 * @author et2301
 * @since 2023-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_car")
public class Car implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 自增主键
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 品牌
   */
  @NotBlank(message = "不能为空")
  private String brand;

  /**
   * 车系
   */
  @NotBlank(message = "不能为空")
  private String series;

  /**
   * 价格, 单位万元
   */
  @NotNull(message = "不能为空")
  private Double price;

  /**
   * 上牌时间
   */
  @NotBlank(message = "不能为空")
  @Pattern(regexp = "^[1-2]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "格式为yyyy-MM-dd")
  private String licenseDate;

  /**
   * 级别
   */
  @NotBlank(message = "不能为空")
  @Pattern(regexp = "\\d{1}", message = "仅支持1位数字值")
  private String level;

  /**
   * 变速箱
   */
  @NotBlank(message = "不能为空")
  @Pattern(regexp = "\\d{1}", message = "仅支持1位数字值")
  private String gearbox;

  /**
   * 排量
   */
  @NotBlank(message = "不能为空")
  @Pattern(regexp = "\\d{1}", message = "仅支持1位数字值")
  private String disp;

  /**
   * 里程, 单位万公里
   */
  @NotNull(message = "不能为空")
  private Double mileage;

  /**
   * 所在省id
   */
  @NotNull(message = "不能为空")
  private Integer province;

  /**
   * 所在市id
   */
  @NotNull(message = "不能为空")
  private Integer city;

  /**
   * 所在区县id
   */
  @NotNull(message = "不能为空")
  private Integer area;

  /**
   * 归属地：省名称-市名称
   */
  private String location;

  /**
   * 图片地址
   */
  @NotBlank(message = "不能为空")
  private String savePath;

  /**
   * 概述
   */
  @NotBlank(message = "不能为空")
  private String summary;

  /**
   * 删除状态0 删除, 1未删除
   */
  private Integer status;

  /**
   * 创建时间
   */
  private String createTime;

  /**
   * 更新时间
   */
  private String updateTime;


}
