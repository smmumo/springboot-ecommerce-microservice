package com.smumo.orderservice.service;

import com.smumo.orderservice.dto.OrderRequestDTO;
import com.smumo.orderservice.entity.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface OrderService {
    List<Orders> getByCustomer(long customerId);

    Orders createOrder(Orders order);
    String placeOrder(OrderRequestDTO orderRequestDTO);

    void cancelOrders(long orderId);
}
