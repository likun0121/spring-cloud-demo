package com.lk.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author LK
 * @date 2021/3/25
 */
@RestController
@RequestMapping("/order-hystrix")
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
public class HystrixController {

    /**
     * HystrixCommand的fallbackMethod属性值是降级处理的回调方法名称，
     * 如果不加属性，则使用DefaultProperties默认的配置
     *
     * @return
     */
//    @HystrixCommand(fallbackMethod = "fallback")
    // 超时配置
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
//    @HystrixCommand(commandProperties = {
//            // 设置熔断开启
//            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
//            // 请求数达到后计算请求异常比例
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            // 休眠时间窗
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            // 错误率
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//    })
    @HystrixCommand
    @GetMapping("/getProductList")
    public String getProductList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8080/product/listByIds",
                Arrays.asList("157875196366160022"), String.class);
        // 也支持服务内部的降级
//        throw new RuntimeException("出现异常了");
    }

    private String fallback() {
        return "太拥挤了，请稍后再试。。。";
    }

    private String defaultFallback() {
        return "默认提示：太拥挤了，请稍后再试。。。";
    }
}
