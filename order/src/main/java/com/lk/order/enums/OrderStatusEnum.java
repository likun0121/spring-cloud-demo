package com.lk.order.enums;

import lombok.Getter;

/**
 * @author LK
 */
@Getter
public enum OrderStatusEnum {

    /**  */
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消");

    /** 状态码 */
    private Integer code;

    /** 说明 */
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
