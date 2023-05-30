package com.hzy.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.hzy.order.mapper")
@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    // bean的注入要放到配置类
    // 带有@SpringBootApplication注解的启动类本身就是配置类

    /**
     *  创建RestTemplate并注入容器
     */
    @Bean
    @LoadBalanced // 负载均衡注解
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     *  作用域全局
     *  负载均衡规则
     * @return 规则
     */
/*    @Bean
    public IRule randomRule(){
        return new RandomRule();
    }*/
}