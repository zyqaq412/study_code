package com.hzy.alipaystarter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @title: PayProperties
 * @Author zxwyhzy
 * @Date: 2023/11/21 19:13
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "alipay")
public class PayProperties {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String gateway;
}
