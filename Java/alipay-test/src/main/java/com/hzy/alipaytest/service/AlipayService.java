package com.hzy.alipaytest.service;

import com.hzy.alipaytest.utils.Order;
import com.hzy.alipaytest.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: AlipayService
 * @Author zxwyhzy
 * @Date: 2023/11/19 20:54
 * @Version 1.0
 */
public interface AlipayService {
    /**
     *  支付接口
     * @param order
     * @return
     */
    R pay(Order order);

    /**
     *  异步通知
     * @param request
     * @return
     */
    R payNotify(HttpServletRequest request);
}
