package com.lk.order.controller;

import com.lk.order.dto.OrderDTO;
import com.lk.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author LK
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        String s = "now " + new Date();
        Message<String> message = MessageBuilder.withPayload(s).build();
        streamClient.output().send(message);
    }

    @GetMapping("/sendOrderMessage")
    public void processOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123");

        Message<OrderDTO> message = MessageBuilder.withPayload(orderDTO).build();
        streamClient.output().send(message);
    }
}
