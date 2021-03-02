package com.lk.order.repository;

import com.lk.order.OrderApplicationTests;
import com.lk.order.dataobject.OrderMaster;
import com.lk.order.enums.OrderStatusEnum;
import com.lk.order.enums.PayStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author LK
 */
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerOpenid("111111");
        orderMaster.setOrderAmount(new BigDecimal("10"));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        assertTrue(result != null);
    }

}