package com.lk.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author LK
 */
@Data
public class ProductVO {

    /** 类目名称 */
    @JsonProperty("name")
    private String categoryName;

    /** 类目编码 */
    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductInfoVO> productInfoVOList;
}
