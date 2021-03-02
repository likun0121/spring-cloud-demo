package com.lk.product.repository;

import com.lk.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LK
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 查询商品类目
     *
     * @param categoryTypeList 类目编码列表
     * @return 商品类目
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
