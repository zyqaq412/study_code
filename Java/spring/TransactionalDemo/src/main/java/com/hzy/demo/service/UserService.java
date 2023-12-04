package com.hzy.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.demo.pojo.User;

/**
 * @title: UserService
 * @Author zxwyhzy
 * @Date: 2023/12/4 13:12
 * @Version 1.0
 */
public interface UserService extends IService<User> {
    /**
     * 测试转账
     */
    String Transfer(Integer fromId, Integer toId, Double money);

    /**
     *  测试付款
     */
    String payment(Integer id,Double money);
}
