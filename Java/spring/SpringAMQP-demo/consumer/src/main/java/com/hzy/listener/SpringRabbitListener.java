package com.hzy.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @title: SpringRabbitListener
 * @Author zxwyhzy
 * @Date: 2023/12/8 14:38
 * @Version 1.0
 */
@Component
@Slf4j
public class SpringRabbitListener {
    // 利用RabbitListener来声明要监听的队列信息
    // 将来一旦监听的队列中有了消息，就会推送给当前服务，调用当前方法，处理消息。
    // 方法体中接收的就是消息体的内容
/*    @RabbitListener(queues = "test.queue1")
    public void listenTestQueueMessage(String msg) {
        System.out.println("spring 消费者接收到消息：【" + msg + "】");
    }*/
/*    @RabbitListener(queues = "test.queue1")
    public void listenTestQueueMessage(Map<String, String> msg) {
        System.out.println("消费者接收到test.queue1消息：【" + msg + "】");
    }*/

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1Message(String msg) {
        System.out.println("spring 消费者接收 fanout.queue1 消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2Message(String msg) {
        System.out.println("spring 消费者接收 fanout.queue2 消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct.queue1", durable = "true"),
            exchange = @Exchange(name = "test.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1Message(String msg) {
        System.out.println("spring 消费者1接收 direct.queue1 消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct.queue2", durable = "true"),
            exchange = @Exchange(name = "test.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2Message(String msg) {
        System.out.println("spring 消费者2接收 direct.queue2 消息：【" + msg + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.queue1", durable = "true"),
            exchange = @Exchange(name = "test.topic", type = ExchangeTypes.TOPIC),
            key = "#.notices"
    ))
    public void listenTopicQueue1Message(String msg) {
        System.out.println("spring 消费者1接收 topic.queue1 消息：【" + msg + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.queue2", durable = "true"),
            exchange = @Exchange(name = "test.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2Message(String msg) {
        System.out.println("spring 消费者2接收 topic.queue2 消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout.queue3", durable = "true"),
            exchange = @Exchange(value = "test.fanout2", type = "fanout")
    ))
    public void listenFanoutQueue3Message(String msg) {
        System.out.println("消费者 接收 fanout.queue3 消息：【" + msg + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "lazy.queue", durable = "true",arguments = {
                    @Argument(name = "x-queue-mode", value = "lazy")
            }),
            exchange = @Exchange(name = "lazy.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenLazyQueueMessage(String msg) {
        System.out.println("消费者 接收 lazy.queue 消息：【" + msg + "】");
    }

    @RabbitListener(queues = "test.queue1")
    public void listenTestQueueMessage(String msg) throws InterruptedException {
        log.info("spring 消费者接收到消息：【" + msg + "】");
        if (true) {
            throw new RuntimeException("故意的");
        }
        log.info("消息处理完成");
    }
}
