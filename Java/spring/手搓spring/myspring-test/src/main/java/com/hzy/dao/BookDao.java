package com.hzy.dao;

import com.hzy.pojo.Book;

/**
 * @title: BookDao
 * @Author zxwyhzy
 * @Date: 2022/11/29 15:58
 * @Version 1.0
 */
public class BookDao {
    public void insert (Book book){
        System.out.println("添加书籍:  "+book);
    }
}
