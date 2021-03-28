package com.lk.product.client;

import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author LK
 */
@FeignClient(name = "product", fallback = ProductClient.Fallback.class)
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listByIds")
    List<ProductInfoDTO> listByIds(List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);

    @Component
    class Fallback implements ProductClient {
        @Override
        public String productMsg() {
            return "异常了";
        }

        @Override
        public List<ProductInfoDTO> listByIds(List<String> productIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<CartDTO> cartDTOList) {

        }
    }
}
