package com.hzy;

import com.hzy.org.myspring.core.ApplicationContext;
import com.hzy.org.myspring.core.ClassPathXmlApplicationContext;
import com.hzy.pojo.Book;
import com.hzy.service.BookService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myspring.xml");
        Book book = (Book) applicationContext.getBean("book");
        BookService bookService =(BookService)applicationContext.getBean("bookService");
        bookService.save(book);
    }
}