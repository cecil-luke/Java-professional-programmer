package com.etoak.entity;

import lombok.Data;

/**
 * 类似购物车
 */
@Data
public class Cart {

    /**
     * 菜品id
     */
    private Integer id;

    /**
     * 菜品名称
     */
    private String name;

    /**
     * 菜品数量
     */
    private Integer num;

}
