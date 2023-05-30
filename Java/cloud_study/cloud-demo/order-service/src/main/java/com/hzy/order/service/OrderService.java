package com.hzy.order.service;


import com.hzy.order.clients.UserClient;
import com.hzy.order.mapper.OrderMapper;
import com.hzy.order.pojo.Order;
import com.hzy.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.用Feign远程调用
        User user = userClient.findById(order.getUserId());
        // 3.封装user到order
        order.setUser(user);
        // 4.返回
        return order;
    }
/*    @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用RestTemplat发起http请求，查询用户
        // 2.1 url路径
        // String url = "http://localhost:8081/user/"+order.getUserId();
        // 将userserver注册后使用服务名访问
        String url = "http://userservice/user/"+order.getUserId();
        // 2.2 发送http请求
        User user = restTemplate.getForObject(url, User.class);
        // 3.封装user到order
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
