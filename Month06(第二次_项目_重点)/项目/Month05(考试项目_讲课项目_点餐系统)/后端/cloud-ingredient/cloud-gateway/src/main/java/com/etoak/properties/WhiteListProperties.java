package com.etoak.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lushisan
 * @Date 2023/6/21 10:28
 * @Title: WhiteListProperties
 * @Description:
 * @Package com.etoak.properties
 */
@Service
@ConfigurationProperties(prefix = "white")
@Data
public class WhiteListProperties {

    private List<String> urls;
}
