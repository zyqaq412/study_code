package com.hzy.alipaystarter.config;


import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.hzy.alipaystarter.core.api.AlipayAPI;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title: AlipayClientConfig
 * @Author zxwyhzy
 * @Date: 2023/11/19 21:02
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(PayProperties.class)
public class AutoConfiguration {

    @Bean
    public AlipayClient getAlipayClient(PayProperties payProperties){
        AlipayClient alipayClient = new DefaultAlipayClient(
                payProperties.getGateway(),
                payProperties.getAppId(),
                payProperties.getAppPrivateKey(),
                AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8,
                payProperties.getAlipayPublicKey(),
                AlipayConstants.SIGN_TYPE_RSA2);
        return alipayClient;
    }
    @Bean
    public AlipayAPI getAlipayApi(PayProperties payProperties,AlipayClient alipayClient){
        return new AlipayAPI(payProperties.getNotifyUrl(),alipayClient);
    }

}
