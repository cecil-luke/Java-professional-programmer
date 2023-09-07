package com.etoak.mapper;

import com.etoak.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author et2302
 * @since 2023-06-26
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 批量添加
     * @param orderItemList
     * @return
     */
    int insertItemList(List<OrderItem> orderItemList);
}
