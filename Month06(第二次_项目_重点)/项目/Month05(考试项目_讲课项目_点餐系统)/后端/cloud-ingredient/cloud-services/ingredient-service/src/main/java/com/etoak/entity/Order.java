package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("t_order")
public class Order implements Serializable {

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
     * 用户id
     */
    private Long userId;

    /**
     * 订单状态: 1:新建订单, 2:已支付, 3:取消
     */
    private Integer orderState;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 预制时间
     */
    private String prepareTime;

    /**
     * 取消时间
     */
    private String cancelTime;


}
