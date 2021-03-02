package com.lk.order.repository;

import com.lk.order.OrderApplicationTests;
import com.lk.order.dataobject.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author LK
 */
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("123456");
        orderDetail.setProductIcon("http://localhost:8090/123");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal("5"));
        orderDetail.setProductQuantity(2);

        orderDetailRepository.save(orderDetail);
    }
}