package com.hzy.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title: PatternProperties
 * @Author zxwyhzy
 * @Date: 2023/4/27 22:21
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateformat;
    private String msg;
    private String testMsg;
}
