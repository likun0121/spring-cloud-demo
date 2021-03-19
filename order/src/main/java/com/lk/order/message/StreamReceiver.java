package com.lk.order.message;

import com.lk.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author LK
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    /*@StreamListener(StreamClient.INPUT)
    public void process(Object message) {
        log.info("StreamReceiver: {}", message);
    }*/

    @StreamListener(StreamClient.QUEUE)
    @SendTo(StreamClient.QUEUE2)
    public String process(OrderDTO message) {
        log.info("order StreamReceiver: {}", message);
        return "received";
    }

    @StreamListener(StreamClient.QUEUE2)
    public void process2(String message) {
        log.info("order StreamReceiver 2: {}", message);
    }
}
