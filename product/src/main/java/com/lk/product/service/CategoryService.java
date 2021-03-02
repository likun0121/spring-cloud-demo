package com.lk.product.service;

import com.lk.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @author LK
 */
public interface CategoryService {

    /**
     * 查询商品类目
     *
     * @param categoryTypeList 类目编码列表
     * @return 商品类目
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
