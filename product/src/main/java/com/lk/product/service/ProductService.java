package com.lk.product.service;

import com.lk.product.dataobject.ProductInfo;
import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;

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

    /**
     * 根据id查询商品
     *
     * @param productIdList id列表
     * @return 商品列表
     */
    List<ProductInfoDTO> listProductByIds(List<String> productIdList);

    void decreaseStock(List<CartDTO> cartDTOList);
}
