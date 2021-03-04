package com.lk.product.repository;

import com.lk.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LK
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 查询上架商品
     *
     * @param productStatus 商品状态
     * @return 上架商品
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 根据id查询商品
     *
     * @param productIdList id列表
     * @return 商品列表
     */
    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
