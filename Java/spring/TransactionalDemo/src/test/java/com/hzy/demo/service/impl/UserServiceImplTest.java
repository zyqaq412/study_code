package com.hzy.demo.service.impl;

import com.hzy.demo.pojo.User;
import com.hzy.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void transfer() {
        String ans = userService.Transfer(1, 2, 600.0);
        assert ans.equals("转账成功");
    }

    @Test
    void payment() {
        userService.payment(1, 500.0);
    }

    @Test
    void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            userService.payment(1, 500.0);
        });
        t1.start();
        // Thread.sleep(2000);
        userService.Transfer(1, 2, 600.0);
    }

    @Test
    void init() {
        User zs = new User();
        zs.setId(1);
        zs.setBalance(10000.0);
        User ls = new User();
        ls.setId(2);
        ls.setBalance(10000.0);
        userService.updateById(zs);
        userService.updateById(ls);
    }

}