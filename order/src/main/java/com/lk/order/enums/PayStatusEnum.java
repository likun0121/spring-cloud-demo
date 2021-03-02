package com.lk.order.enums;

import lombok.Getter;

/**
 * @author LK
 */
@Getter
public enum PayStatusEnum {

    /**  */
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),;

    /** 状态码 */
    private Integer code;

    /** 说明 */
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
