package com.hzy.alipaytest.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: AlipayClientConfig
 * @Author zxwyhzy
 * @Date: 2023/11/19 21:02
 * @Version 1.0
 */
@Configuration
public class AlipayClientConfig {
    @Autowired
    private AliPayConfig aliPayConfig;

    @Bean
    public AlipayClient getAlipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayConfig.getGateway(),
                aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(),
                AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8,
                aliPayConfig.getAlipayPublicKey(),
                AlipayConstants.SIGN_TYPE_RSA2);
        return alipayClient;
    }
}
