package com.lk.order.controller;

import com.lk.product.client.ProductClient;
import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
     *
     * @return String
     */
    @GetMapping("/get_product_msg_feign")
    public String getProductMsg() {
        String response = productClient.productMsg();
        log.info("feign response={}", response);
        return response;
    }

    @GetMapping("/getProductList")
    public List<ProductInfoDTO> getProductList() {
        return productClient.listByIds(Arrays.asList("157875227953464068"));
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setProductId("164103465734242707");
        cartDTO.setProductQuantity(3);
        productClient.decreaseStock(Arrays.asList(cartDTO));
        return "success";
    }
}
