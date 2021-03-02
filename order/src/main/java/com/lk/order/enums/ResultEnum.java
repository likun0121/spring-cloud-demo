package com.lk.order.enums;

import lombok.Getter;

/**
 * @author LK
 */
@Getter
public enum ResultEnum {

    /** 参数错误 */
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
