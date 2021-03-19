package com.lk.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author LK
 */
@Slf4j
@Component
public class MqReceiver {

    // 1.@RabbitListener(queues = "myQueue")
    // 2.自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 3.自动创建，Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MyReceiver: {}", message);
    }

    /**
     * 数码产品服务 接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "3C",
            value = @Queue("3CQueue")
    ))
    public void process3C(String message) {
        log.info("3C MyReceiver: {}", message);
    }

    /**
     * 水果供应服务 接收消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitQueue")
    ))
    public void processFruit(String message) {
        log.info("fruit MyReceiver: {}", message);
    }
}
