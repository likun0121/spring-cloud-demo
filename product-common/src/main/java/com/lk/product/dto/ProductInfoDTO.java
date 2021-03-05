package com.lk.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LK
 */
@Data
public class ProductInfoDTO {

    /** 商品id */
    private String productId;

    /** 名称 */
    private String productName;

    /** 单价 */
    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    /** 描述 */
    private String productDescription;

    /** 图片 */
    private String productIcon;

    /** 状态，0正常1下架 */
    private Integer productStatus;

    /** 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
