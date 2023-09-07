package com.etoak.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取Nacos配置中心中以image开头的自定义配置项
 */
@Component
@ConfigurationProperties(prefix = "image")
@Data
public class ImageProperties {

    private String dir;

    private String prefix;

    private List<String> types;
}
