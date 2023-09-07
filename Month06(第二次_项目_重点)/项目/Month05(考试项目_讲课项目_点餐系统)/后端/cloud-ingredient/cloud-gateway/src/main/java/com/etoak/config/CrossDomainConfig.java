package com.etoak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * 配置CORS跨域(Cross Origin Resource Share)
 */
@Configuration
public class CrossDomainConfig {

    /**
     * spring-web提供的跨域过滤器
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        /* Spring MVC项目中使用
           import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
           import org.springframework.web.filter.CorsFilter;

           Spring WebFlux(以Gateway为例)
           import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
           import org.springframework.web.cors.reactive.CorsWebFilter;
         */
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");

        List<String> methods = Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS");
        corsConfiguration.setAllowedMethods(methods);
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }

}
