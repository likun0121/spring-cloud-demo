package com.lk.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author LK
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 1.使用 {@link RestTemplate} 直接访问固定的硬编码服务地址
     * @return String
     */
    @GetMapping("/get_product_msg_1")
    public String getProductMsg1() {
        // 1.第一种方式
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("response1={}", response);
        return response;
    }

    /**
     * 2.使用 {@link LoadBalancerClient} 获取对应服务地址，使用 {@link RestTemplate} 访问
     * @return String
     */
    @GetMapping("/get_product_msg_2")
    public String getProductMsg2() {
        // 2.第二种方式
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort())
                + "/msg";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response2={}", response);
        return response;
    }

    /**
     * 3.使用 {@link org.springframework.cloud.client.loadbalancer.LoadBalanced} 注解作用在 {@link RestTemplate} 的生成bean的方法上，
     * 使注入的RestTemplate实例具有负载均衡的作用
     * @return String
     */
    @GetMapping("/get_product_msg_3")
    public String getProductMsg3() {
        // 3.第三种方式
        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
        log.info("response3={}", response);
        return response;
    }
}
