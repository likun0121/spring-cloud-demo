package com.lk.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author LK
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.toString();
        return "this is product server " + time;
    }

}
