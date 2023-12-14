package com.hzy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testQueue1() {
        // 队列名称
        String queueName = "test.queue1";
        // 消息
        String message = "hello";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }
    @Test
    public void SendFanoutExchange() {
        // 交换机名称
        String exchangeName = "test.fanout";
        // 消息
        String message = "hello, test.fanout";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
    @Test
    public void SendDirectExchange() {
        // 交换机名称
        String exchangeName = "test.direct";
        // 消息
        String message = "hello, blue";
        rabbitTemplate.convertAndSend(exchangeName, "a.b", message);
    }
    @Test
    public void SendTopicExchange() {
        // 交换机名称
        String exchangeName = "test.topic";
        // 消息
        String message = "邮件通知";
        rabbitTemplate.convertAndSend(exchangeName, "mail.notices", message);

        message = "微信通知";
        rabbitTemplate.convertAndSend(exchangeName, "wechat.notices", message);
        message = "今日新闻";
        rabbitTemplate.convertAndSend(exchangeName, "today.news", message);
    }

    @Test
    public void testSendMapQueue1() {
        // 队列名称
        String queueName = "test.queue1";
        // 消息
        Map<String,String> map = new HashMap<>();
        map.put("name","zs");
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, map);
    }


    @Test
    public void testConfirm1() {
        // 1.创建CorrelationData 传入id
        /* public CorrelationData() {
            this.id = UUID.randomUUID().toString();
        }*/
        CorrelationData cd = new CorrelationData();
        // 2.给Future添加ConfirmCallback
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                // 2.1.Future发生异常时的处理逻辑，基本不会触发
                log.error("send message fail", ex);
            }
            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                // 2.2.Future接收到回执的处理逻辑，参数中的result就是回执内容
                if(result.isAck()){ // result.isAck()，boolean类型，true代表ack回执，false 代表 nack回执
                    log.debug("发送消息成功，收到 ack!");
                }else{ // result.getReason()，String类型，返回nack时的异常描述
                    log.error("发送消息失败，收到 nack, reason : {}", result.getReason());
                }
            }
        });
        // 3.发送消息
        rabbitTemplate.convertAndSend("test.topic", "aaa", "hello", cd);

    }

    @Test
    public void testQueue1Confirm() {
        // 队列名称
        String queueName = "test.queue1";
        // 消息
        String message = "hello";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
