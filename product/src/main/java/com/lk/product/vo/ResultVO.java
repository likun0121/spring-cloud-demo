package com.lk.product.vo;

import lombok.Data;

/**
 * 返回结果最外层封装对象
 * @author LK
 */
@Data
public class ResultVO<T> {

    /** 返回码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体数据 */
    private T data;

}
