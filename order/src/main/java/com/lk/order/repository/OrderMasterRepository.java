package com.lk.order.repository;

import com.lk.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LK
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
