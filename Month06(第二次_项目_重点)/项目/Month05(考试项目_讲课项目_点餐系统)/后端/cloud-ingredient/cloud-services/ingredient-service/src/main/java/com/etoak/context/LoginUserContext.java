package com.etoak.context;

import com.etoak.entity.User;

public class LoginUserContext {

    /**
     * 默认将当前线程与User进行关联
     */
    private ThreadLocal<User> threadLocal;

    private LoginUserContext() {
        threadLocal = new ThreadLocal<>();
    }

    /**
     * 关联当前线程
     */
    public static void setUser(User user) {
        SingletonLoginUserContext.loginUserContext.threadLocal.set(user);
    }

    /**
     * 获取与当前线程绑定的对象
     */
    public static User getLoginUser() {
        return SingletonLoginUserContext.loginUserContext.threadLocal.get();
    }

    /**
     * 移除线程和线程关联的内容
     */
    public static void removeUser() {
        SingletonLoginUserContext.loginUserContext.threadLocal.remove();
    }


    private static class SingletonLoginUserContext {

        private static final LoginUserContext loginUserContext = new LoginUserContext();;

        private SingletonLoginUserContext() {

        }
    }
}
