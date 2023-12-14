package com.hzy.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

/**
 * @title: FanoutConfiguration
 * @Author zxwyhzy
 * @Date: 2023/12/8 16:32
 * @Version 1.0
 */

public class FanoutConfiguration {
    @Bean
    public FanoutExchange fanoutExchange(){
        ExchangeBuilder exchangeBuilder = ExchangeBuilder.fanoutExchange("test.fanout2");
        return exchangeBuilder.build();
    }
    @Bean
    public Queue fanoutQueue3(){
        // durable() 持久化队列
        QueueBuilder durable = QueueBuilder.durable("fanout.queue3");
        return durable.build();
    }
    @Bean
    public Binding fanoutBinding3(FanoutExchange fanoutExchange,Queue fanoutQueue3){
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }
}
