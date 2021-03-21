package com.lk.user.enums;

import lombok.Getter;

/**
 * @author LK
 */
@Getter
public enum ResultEnum {

    /**
     * 登录失败
     */
    LOGIN_FAILED(1, "登录失败"),
    ROLE_ERROR(2, "用户角色错误"),;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
