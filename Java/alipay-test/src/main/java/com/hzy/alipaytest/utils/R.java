package com.hzy.alipaytest.utils;

import lombok.Data;

/**
 * @title: R
 * @Author zxwyhzy
 * @Date: 2023/11/19 15:57
 * @Version 1.0
 */
@Data
public class R {
    private String data;
    private String msg;
    private int code;

    public R() {
    }

    public R (String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public R( String msg, int code,String data) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static R ok(){
        return new R("成功",200);
    }
    public static R ok(String data){
        return new R("成功",200,data);
    }
    public static R fail(String message, int code) {
        return new R( message,code);
    }
}
