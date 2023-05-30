package com.hzy.pojo;

/**
 * @title: Book
 * @Author zxwyhzy
 * @Date: 2022/11/29 15:57
 * @Version 1.0
 */
public class Book {
    private String name;
    private String zName;

    public void setName(String name) {
        this.name = name;
    }

    public void setZName(String zName) {
        this.zName = zName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", zName='" + zName + '\'' +
                '}';
    }
}
