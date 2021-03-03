package com.lk.order.controller;

import com.lk.order.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LK
 */
@RestController
@Slf4j
public class FeignClientController {

    @Autowired
    private ProductClient productClient;

    /**
     * 使用 {@link org.springframework.cloud.netflix.feign.FeignClient}
     * @return String
     */
    @GetMapping("/get_product_msg_feign")
    public String getProductMsg() {
        String response = productClient.productMsg();
        log.info("feign response={}", response);
        return response;
    }

}
