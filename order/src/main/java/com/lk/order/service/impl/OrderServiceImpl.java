package com.lk.order.service.impl;

import com.lk.order.dataobject.OrderDetail;
import com.lk.order.dataobject.OrderMaster;
import com.lk.order.dataobject.ProductInfo;
import com.lk.order.dto.OrderDTO;
import com.lk.order.enums.OrderStatusEnum;
import com.lk.order.enums.PayStatusEnum;
import com.lk.order.repository.OrderDetailRepository;
import com.lk.order.repository.OrderMasterRepository;
import com.lk.order.service.OrderService;
import com.lk.order.util.KeyUtil;
import com.lk.product.client.ProductClient;
import com.lk.product.dto.CartDTO;
import com.lk.product.dto.ProductInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LK
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // 生成订单id
        String orderId = KeyUtil.genUniqueKey();

        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoDTO> productInfoDTOList = productClient.listByIds(productIdList);
        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoDTO productInfoDTO : productInfoDTOList) {
                if (productInfoDTO.getProductId().equals(orderDetail.getProductId())) {
                    // 单价 * 数量
                    orderAmount = productInfoDTO.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);

                    BeanUtils.copyProperties(productInfoDTO, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setCreateTime(null);
                    orderDetail.setUpdateTime(null);
                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

        }
        // 扣库存（调用商品服务）
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        // 订单入库
        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
