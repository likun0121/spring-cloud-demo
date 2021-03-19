package com.lk.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author LK
 */
public interface StreamClient {

    String QUEUE = "myMessage";
    String QUEUE2 = "myMessage2";

    @Input(QUEUE)
    SubscribableChannel input();

    @Output(QUEUE)
    MessageChannel output();

    @Input(QUEUE2)
    SubscribableChannel input2();

    @Output(QUEUE2)
    MessageChannel output2();
}
