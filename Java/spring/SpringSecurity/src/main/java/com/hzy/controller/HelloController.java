package com.hzy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: HelloController
 * @Author zxwyhzy
 * @Date: 2022/12/19 18:32
 * @Version 1.0
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:del')")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/hello2")
    @PreAuthorize("hasAuthority('admin')")
    public String hello2(){
        return "hello2";
    }



}
