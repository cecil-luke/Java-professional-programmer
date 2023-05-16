package com.etoak.config;

import com.etoak.common.properties.ImageProperties;
import com.etoak.filter.CrossFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Autowired
  ImageProperties imageProperties;

  /**
   * <mvc:resources mapping="/pics/**" location="file:d:/upload/et2301/" />
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // /pics/**
    String mapping = imageProperties.getPathPrefix().endsWith("/") ?
      imageProperties.getPathPrefix() + "**" :
      imageProperties.getPathPrefix() + "/**";

    // location
    String location = imageProperties.getDir().endsWith("/") ?
      "file:" + imageProperties.getDir() :
      "file:" + imageProperties.getDir() + "/";

    registry.addResourceHandler(mapping)
      .addResourceLocations(location);
  }

  /**
   * 注册过滤器
   */
  @Bean
  public FilterRegistrationBean<CrossFilter> crossFilter() {
    FilterRegistrationBean<CrossFilter> registration = new FilterRegistrationBean<>();
    // <filter-class>
    registration.setFilter(new CrossFilter());

    // url-pattern
    registration.addUrlPatterns("/*");

    // 设置执行顺序
    registration.setOrder(-100);

    return registration;
  }

}
