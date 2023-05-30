package com.hzy.service;

import com.hzy.dao.BookDao;
import com.hzy.pojo.Book;

/**
 * @title: BookService
 * @Author zxwyhzy
 * @Date: 2022/11/29 15:59
 * @Version 1.0
 */
public class BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void save(Book book){
        bookDao.insert(book);
    }
}
