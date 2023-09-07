package com.etoak.vo;

import com.etoak.entity.Cart;
import lombok.Data;

import java.util.List;

/**
 * @Author Lushisan
 * @Date 2023/6/26 15:03
 * @Title: CartVO
 * @Description:
 * @Package com.etoak.vo
 */
@Data
public class CartVO {

    /**
     * 预制时间
     */
    private String prepareTime;

    /**
     * 购物车的菜品列表
     */
    List<Cart> cartList;
}
