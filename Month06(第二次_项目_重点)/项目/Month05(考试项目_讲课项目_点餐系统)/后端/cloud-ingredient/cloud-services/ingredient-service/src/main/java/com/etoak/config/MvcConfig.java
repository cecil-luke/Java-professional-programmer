package com.etoak.config;

import com.etoak.interceptor.LoginUserInterceptor;
import com.etoak.properties.ImageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    ImageProperties imageProperties;

    @Autowired
    LoginUserInterceptor loginUserInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /pics/**
        String path = getPicPath();

        // file:d:/upload/et2302/
        String location = imageProperties.getDir().endsWith("/") ?
                "file:" + imageProperties.getDir() :
                "file:" + imageProperties.getDir() + "/";

        registry.addResourceHandler(path) // 请求地址
            .addResourceLocations(location); // 资源位置
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /pics/**
        getPicPath();

        registry.addInterceptor(loginUserInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/user/login")
                //.excludePathPatterns("/user/logout")
                .excludePathPatterns("/user/register");
    }

    private String getPicPath() {
        return imageProperties.getPrefix().endsWith("/") ?
                imageProperties.getPrefix() + "**" :
                imageProperties.getPrefix() + "/**";
    }
}
