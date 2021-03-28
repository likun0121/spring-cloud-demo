package com.lk.product.controller;

import com.lk.product.dataobject.ProductCategory;
import com.lk.product.dataobject.ProductInfo;
import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;
import com.lk.product.service.CategoryService;
import com.lk.product.service.ProductService;
import com.lk.product.util.ResultVoUtil;
import com.lk.product.vo.ProductInfoVO;
import com.lk.product.vo.ProductVO;
import com.lk.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LK
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询所有再加商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list() {
        // 1.查询所有再加商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2.获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        // 3.查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 4.构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);

            productVOList.add(productVO);
        }

        return ResultVoUtil.success(productVOList);
    }

    /**
     * 根据id获取商品列表
     *
     * @param productIdList 商品id
     * @return
     */
    @PostMapping("/listByIds")
    public List<ProductInfoDTO> listByIds(@RequestBody List<String> productIdList) {
        try {
            // 用于测试服务调用的熔断处理
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.listProductByIds(productIdList);
    }

    @PostMapping("/decreaseStock")
    private void decreaseStock(@RequestBody List<CartDTO> cartDTOList) {
        productService.decreaseStock(cartDTOList);
    }
}
