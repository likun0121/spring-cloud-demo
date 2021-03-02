package com.lk.order.dto;

import com.lk.order.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LK
 */
@Data
public class OrderDTO {

    /** 订单id */
    private String orderId;

    /** 买家姓名 */
    private String buyerName;

    /** 买家手机号 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信openid */
    private String buyerOpenid;

    /** 订单总金额 */
    private BigDecimal orderAmount;

    /** 订单状态，默认为0新下单 */
    private Integer orderStatus;

    /** 支付状态，默认为0未支付 */
    private Integer payStatus;

    /** 订单详情列表 */
    private List<OrderDetail> orderDetailList;
}
