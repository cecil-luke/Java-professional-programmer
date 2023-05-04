package com.etoak.rst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Lushisan
 * @Date 2023/4/25 15:45
 * @Title: ETMVCConfig
 * @Description:
 * @Package com.etoak.rst.config
 */
@Configuration
@EnableWebMvc
public class ETMVCConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
