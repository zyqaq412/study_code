package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;
import com.hzy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: LoginController
 * @Author zxwyhzy
 * @Date: 2022/12/19 19:40
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginServcie.login(user);
    }
    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return loginServcie.logout();
    }
}
