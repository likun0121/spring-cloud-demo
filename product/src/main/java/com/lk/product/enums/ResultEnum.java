package com.lk.product.enums;

import lombok.Getter;

/**
 * @author LK
 */
@Getter
public enum ResultEnum {

    /** 商品不存在 */
    PRODUCT_NOT_EXIST(1, "商品不存在"),

    PRODUCT_STOCK_LACKING(2, "库存不足"),;

    /** 编码 */
    private Integer code;

    /** 描述 */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
