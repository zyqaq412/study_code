package com.hzy.alipaytest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title: AliPayConfig 支付宝支付配置类
 * @Author zxwyhzy
 * @Date: 2023/11/19 15:33
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;
    private String gateway;
}

