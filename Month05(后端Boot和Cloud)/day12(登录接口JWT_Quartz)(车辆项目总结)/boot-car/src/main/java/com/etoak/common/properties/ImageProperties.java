package com.etoak.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取application.yml以image开头的配置项
 */
@Component
@ConfigurationProperties(prefix = "image")
@Data
public class ImageProperties {

  /**
   * 图片上传目录
   */
  private String dir;

  /**
   * 图片访问前缀
   */
  private String pathPrefix;

  /**
   * 允许上传的图片类型
   */
  private List<String> types;
}
