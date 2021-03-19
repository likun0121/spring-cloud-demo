package com.lk.order.message;

import com.alibaba.fastjson.JSONObject;
import com.lk.product.dto.ProductInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LK
 */
@Slf4j
@Component
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message => ProductInfoDTO
        List<ProductInfoDTO> productInfoDTOList = JSONObject.parseArray(message, ProductInfoDTO.class);
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoDTOList);

        // 存储到redis中
        for (ProductInfoDTO productInfoDTO : productInfoDTOList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoDTO.getProductId()),
                    String.valueOf(productInfoDTO.getProductStock()));
        }
    }

}
