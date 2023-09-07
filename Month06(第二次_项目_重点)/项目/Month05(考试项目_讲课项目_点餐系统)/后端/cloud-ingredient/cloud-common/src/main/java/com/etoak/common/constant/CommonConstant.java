package com.etoak.common.constant;

public class CommonConstant {

    /**
     * 菜品类型
     */
    public static final int DISHES_TYPE = 1;

    /**
     * 主要食材类型
     */
    public static final int MAIN_INGREDIENT_TYPE = 2;

    /**
     * 辅助食材类型
     */
    public static final int MINOR_INGREDIENT_TYPE = 3;

    /**
     * 调料类型
     */
    public static final int SEASONING_TYPE = 4;

    /**
     * 验证码存入Redis的key前缀
     */
    public static final String REDIS_CODE_PREFIX = "code:";

    /**
     * 购物车存入Redis的key前缀
     */
    public static final String REDIS_CART_PREFIX = "cart:";


    /**
     * Redis存贮登录用户的Key前缀
     */
    public static final String REDIS_LOGIN_PREFIX = "login:user:";

    /**
     * 1新建订单 2已支付 3取消
     */
    public static final int ORDER_CREATE = 1;

    public static final int ORDER_PAY = 2;

    public static final int ORDER_CANCEL = 3;

}
