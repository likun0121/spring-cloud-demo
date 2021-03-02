package com.lk.product.repository;

import com.lk.product.ProductApplicationTests;
import com.lk.product.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author LK
 */
public class ProductCategoryRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(list.size() > 0);
    }
}