package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author et2302
 * @since 2023-06-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 菜品id
     */
    private Integer ingredientId;

    /**
     * 菜品名称
     */
    private String ingredientName;

    /**
     * 菜品数量
     */
    private Integer ingredientNum;


}
