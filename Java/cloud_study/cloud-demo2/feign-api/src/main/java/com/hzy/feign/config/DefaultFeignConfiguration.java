package com.hzy.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @title: DefaultFeignConfiguration
 * @Author zxwyhzy
 * @Date: 2023/5/6 22:33
 * @Version 1.0
 */
public class DefaultFeignConfiguration  {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.BASIC; // 日志级别为BASIC
    }
}
