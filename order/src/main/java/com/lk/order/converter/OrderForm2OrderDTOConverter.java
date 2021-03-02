package com.lk.order.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lk.order.dataobject.OrderDetail;
import com.lk.order.dto.OrderDTO;
import com.lk.order.enums.ResultEnum;
import com.lk.order.exception.OrderException;
import com.lk.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LK
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> list = new ArrayList<>();

        Gson gson = new Gson();
        try {
            list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【json抓换】错误，string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(list);

        return orderDTO;
    }

}
