package com.hzy.alipaystarter.core.api;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.hzy.alipaystarter.core.dtos.Order;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: AlipayAPI
 * @Author zxwyhzy
 * @Date: 2023/11/21 19:18
 * @Version 1.0
 */
@AllArgsConstructor
public class AlipayAPI {
    private String notifyUrl;
    private AlipayClient alipayClient;
    public String pay(Order order){
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //
        request.setReturnUrl(notifyUrl);
        // 异步通知的地址
        request.setNotifyUrl(notifyUrl);
        Map<String,String> map = new HashMap<>();
        map.put("out_trade_no",order.getOrderId());
        map.put("total_amount",order.getPrice());
        map.put("subject",order.getSubject());
        map.put("body",order.getBody());
        map.put("product_code","FAST_INSTANT_TRADE_PAY");

        // 设置业务参数
        request.setBizContent(JSONObject.toJSONString(map));

        // 发起支付请求
        // 发起支付请求
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        // 获取响应结果
        if (response.isSuccess()) {
            System.out.println("调用成功");
            System.out.println("支付宝支付链接：" + response.getBody());
            return response.getBody();
        } else {
            System.out.println("调用失败");
            System.out.println("错误信息：" + response.getMsg());
            return "支付失败";
        }
    }
}
