package com.hzy.order.clients;

import com.hzy.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @title: UserClient
 * @Author zxwyhzy
 * @Date: 2023/5/5 16:38
 * @Version 1.0
 */
@FeignClient("userservice")// 服务名
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
