package com.lk.order.service;

import com.lk.order.dto.OrderDTO;

/**
 * @author LK
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO 订单详情
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 完结订单（只能卖家操作）
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
