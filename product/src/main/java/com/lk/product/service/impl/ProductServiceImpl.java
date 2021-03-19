package com.lk.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lk.product.dataobject.ProductInfo;
import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;
import com.lk.product.enums.ProductStatusEnum;
import com.lk.product.enums.ResultEnum;
import com.lk.product.exception.ProductException;
import com.lk.product.repository.ProductInfoRepository;
import com.lk.product.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author LK
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoDTO> listProductByIds(List<String> productIdList) {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductIdIn(productIdList);
        return productInfoList.stream().map(e -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(e, productInfoDTO);
            return productInfoDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);
        List<ProductInfoDTO> productInfoDTOList = productInfoList.stream().map(e -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            BeanUtils.copyProperties(e, productInfoDTO);
            return productInfoDTO;
        }).collect(Collectors.toList());
        // 发送mq消息
        amqpTemplate.convertAndSend("productInfo", JSONObject.toJSONString(productInfoDTOList));

    }

    @Transactional(rollbackFor = Exception.class)
    public List<ProductInfo> decreaseStockProcess(List<CartDTO> cartDTOList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> optionalProductInfo = productInfoRepository.findById(cartDTO.getProductId());
            // 获取商品，若商品不存在抛出异常
            ProductInfo productInfo = optionalProductInfo.orElseThrow(() -> new ProductException(ResultEnum.PRODUCT_NOT_EXIST));
            // 判断内存是否足够
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_LACKING);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
