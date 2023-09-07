package com.etoak.service.impl;

import cn.hutool.json.JSONUtil;
import com.etoak.common.constant.CommonConstant;
import com.etoak.context.LoginUserContext;
import com.etoak.entity.Cart;
import com.etoak.entity.User;
import com.etoak.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void modifyCart(Cart cart) {     // 购物车Cart:id,name,num
        String key = getCartKey();

        // field
        // value 的 filed
        String field = String.valueOf(cart.getId());

        // value
        // Cart 转 String
        String value = JSONUtil.toJsonStr(cart);
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public List<Cart> list() {
        // 获取当前登录用户
        String key = getCartKey();
        List<Object> carList = stringRedisTemplate.opsForHash().values(key);
        // 转成Cart
        return carList.stream()
                // toBean, JSON字符串转为实体类对象，转换异常将被抛出
                .map(json -> JSONUtil.toBean(json.toString(), Cart.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIngredient(int ingredientId) {
        // 获取当前登录用户
        String key = getCartKey();
        stringRedisTemplate.opsForHash().delete(key, String.valueOf(ingredientId));
    }

    /**
     * 公共方法
     * @return
     */
    private String getCartKey() {
        // 获取当前登录用户
        User loginUser = LoginUserContext.getLoginUser();

        // key: cart:用户id
        return CommonConstant.REDIS_CART_PREFIX + loginUser.getId();
    }
}
