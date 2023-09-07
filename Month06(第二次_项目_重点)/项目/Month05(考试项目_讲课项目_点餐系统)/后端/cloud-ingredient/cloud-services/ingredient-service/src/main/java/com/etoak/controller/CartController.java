package com.etoak.controller;

import com.etoak.common.vo.Result;
import com.etoak.entity.Cart;
import com.etoak.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    /**
     * 添加或减少菜品数量
     */
    @PostMapping
    public Result modifyCart(@RequestBody Cart cart) {
        cartService.modifyCart(cart);
        return Result.success(StringUtils.EMPTY);
    }

    /**
     * 获取预制菜品列表
     */
    @GetMapping
    public Result list() {
        List<Cart> cartList = cartService.list();
        return Result.success(cartList);
    }

    /**
     * 删除购物车中的菜品
     *
     * @param ingredientId
     */
    @DeleteMapping("/{ingredientId}")
    public Result deleteIngredient(@PathVariable int ingredientId) {
        cartService.deleteIngredient(ingredientId);
        return Result.success(StringUtils.EMPTY);
    }
}
