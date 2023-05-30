package com.hzy;

import com.hzy.domain.entity.Menu;
import com.hzy.domain.entity.User;
import com.hzy.mapper.MenuMapper;
import com.hzy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @title: mapperTest
 * @Author zxwyhzy
 * @Date: 2022/12/19 19:04
 * @Version 1.0
 */
@SpringBootTest
public class mapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;


    @Test
    public void selectPermsByUserIdTest(){
        List<String> list = menuMapper.selectPermsByUserId(1L);
        //list.forEach(a -> System.out.println(a));
        System.out.println(list);

    }
    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);//$2a$10$thltlvKtDb4SAth6C5z3E.zPmSDYRp4LNXb1R0aJ15L.iyFlPMK3i
        System.out.println(passwordEncoder
                .matches("123",
                        "$2a$10$thltlvKtDb4SAth6C5z3E.zPmSDYRp4LNXb1R0aJ15L.iyFlPMK3i"));
    }
}
