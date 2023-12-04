package com.hzy.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @title: User
 * @Author zxwyhzy
 * @Date: 2023/12/4 13:08
 * @Version 1.0
 */
@Data
@TableName("users")
public class User {
    private int id;
    private String userName;
    private Double balance;
}
