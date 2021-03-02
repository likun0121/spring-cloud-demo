package com.lk.product.enums;

import lombok.Getter;

/**
 * 商品上下架状态
 * @author LK
 */
@Getter
public enum ProductStatusEnum {

    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    /** 编码 */
    private Integer code;

    /** 描述 */
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
