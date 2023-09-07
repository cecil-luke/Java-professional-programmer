package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.etoak.common.group.DishesGroup;
import com.etoak.common.group.IngredientGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author et2302
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_ingredient")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @NotBlank(message = "必填", groups = {DishesGroup.class, IngredientGroup.class})
    @Length(min = 2, max = 20, message = "长度为2-20个字符", groups = {DishesGroup.class, IngredientGroup.class})
    private String name;

    /**
     * 图片
     */
    @NotBlank(message = "必填", groups = {DishesGroup.class, IngredientGroup.class})
    private String url;

    /**
     * 主要食材
     */
    @NotNull(message = "必填", groups = {DishesGroup.class})
    private Integer mainIngredient;

    /**
     * 主要食材份量（单位g）
     */
    @NotNull(message = "必填", groups = {DishesGroup.class})
    private Integer mainIngredientNum;

    /**
     * 辅助食材
     */
    @NotNull(message = "必填", groups = {DishesGroup.class})
    private Integer minorIngredient;

    /**
     * 辅助食材份量（单位g）
     */
    @NotNull(message = "必填", groups = {DishesGroup.class})
    private Integer minorIngredientNum;

    /**
     * 调料
     */
    @NotNull(message = "必填", groups = {DishesGroup.class})
    private Integer seasoning;

    /**
     * 调料份量（单位g）
     */
    @NotNull(message = "必填", groups = {DishesGroup.class})
    private Integer seasoningNum;

    /**
     * 菜品/食材介绍
     */
    @NotBlank(message = "必填", groups = {DishesGroup.class, IngredientGroup.class})
    private String description;

    /**
     * 菜品/食材标识（1-菜品，2-主食，3-辅食，4-调料）
     */
    private Integer ingredientType;

    /**
     * 删除状态(1: 未删除, 2: 已删除)
     */
    private Integer deleteState;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
