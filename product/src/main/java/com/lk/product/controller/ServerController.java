package com.lk.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LK
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    public String msg() {
        return "this is product server 2";
    }

}
