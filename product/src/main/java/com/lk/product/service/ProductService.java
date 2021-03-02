package com.lk.product.service;

import com.lk.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @author LK
 */
public interface ProductService {

    /**
     * 查询所有上架商品
     *
     * @return 所有上架商品
     */
    List<ProductInfo> findUpAll();
}
