package com.lk.product.service;

import com.lk.product.ProductApplicationTests;
import com.lk.product.dataobject.ProductCategory;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author LK
 */
public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = categoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assert.assertTrue(list.size() > 0);
    }
}