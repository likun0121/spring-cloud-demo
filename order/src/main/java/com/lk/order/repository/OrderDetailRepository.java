package com.lk.order.repository;

import com.lk.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LK
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
