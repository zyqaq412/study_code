package com.hzy.alipaytest.controller;

import com.alipay.api.AlipayApiException;
import com.hzy.alipaytest.service.AlipayService;
import com.hzy.alipaytest.utils.Order;
import com.hzy.alipaytest.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: AlipayController
 * @Author zxwyhzy
 * @Date: 2023/11/19 16:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayService alipayService;

    /**
     *  支付接口
     * @param order 订单
     * @return
     */
    @PostMapping("/pay")
    public R pay(@RequestBody Order order){
        // 根据时间戳生成订单号
        order.setOrderId(String.valueOf(System.currentTimeMillis()));
        return alipayService.pay(order);
    }

    /**
     *  订单状态通知 异步通知
     * @param request
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/notify")
    public R payNotify(HttpServletRequest request) throws AlipayApiException {
        return alipayService.payNotify(request);
    }
}
