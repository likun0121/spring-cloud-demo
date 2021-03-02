package com.lk.product.service.impl;

import com.lk.product.dataobject.ProductInfo;
import com.lk.product.enums.ProductStatusEnum;
import com.lk.product.repository.ProductInfoRepository;
import com.lk.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LK
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
