package com.lk.order.service.impl;

import com.lk.order.dataobject.OrderMaster;
import com.lk.order.dto.OrderDTO;
import com.lk.order.enums.OrderStatusEnum;
import com.lk.order.enums.PayStatusEnum;
import com.lk.order.repository.OrderDetailRepository;
import com.lk.order.repository.OrderMasterRepository;
import com.lk.order.service.OrderService;
import com.lk.order.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author LK
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
//      todo 查询商品信息（调用商品服务）
//      todo 计算总价
//      todo 扣库存（调用商品服务）
//      订单入库

        // 生成订单id
        String orderId = KeyUtil.genUniqueKey();

        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        // todo 需要计算
        orderMaster.setOrderAmount(new BigDecimal("5"));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
