package com.etoak.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user")
@Component
@Data
public class UserProperties {

    private int id;

    private String name;

}
