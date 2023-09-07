package com.etoak.service;

import com.etoak.entity.Cart;

import java.util.List;

public interface CartService {

    /**
     * 添加或减少菜品数量
     */
    void modifyCart(Cart cart);

    /**
     * 获取所有的菜品信息
     *
     * @return
     */
    List<Cart> list();

    /**
     * 删除购物车中的菜品
     *
     * @param ingredientId
     */
    void deleteIngredient(int ingredientId);
}
