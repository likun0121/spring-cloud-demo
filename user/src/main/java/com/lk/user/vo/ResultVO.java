package com.lk.user.vo;

import lombok.Data;

/**
 * @author LK
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;

}