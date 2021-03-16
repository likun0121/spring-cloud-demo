package com.lk.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LK
 */
@RestController
@RequestMapping("/conf")
@RefreshScope
public class ConfigBusController {

    @Value("${env}")
    private String env;

    @GetMapping("/env")
    public String printEnv() {
        return env;
    }

}
