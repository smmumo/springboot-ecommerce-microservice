package com.smumo.orderservice.service;

import com.smumo.orderservice.dto.OrderLineItemsDTO;
import com.smumo.orderservice.dto.OrderRequestDTO;
import com.smumo.orderservice.entity.OrderLineItems;
import com.smumo.orderservice.entity.Orders;
import com.smumo.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Orders> getByCustomer(long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        Orders orders = new Orders();
        orders.setOrderNumber(UUID.randomUUID().toString());
       var orderItems = orderRequestDTO.getOrderLineItemsDTOList().stream().map(this::mapToDto).toList();
       orders.setOrderLineItemsList(orderItems);

       orderRepository.save(orders);
    }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems items = new OrderLineItems();
        items.setQuantity(orderLineItemsDTO.getQuantity());
        items.setPrice(orderLineItemsDTO.getPrice());
        items.setSkuCode(orderLineItemsDTO.getSkuCode());
        return  items;
    }

    @Override
    public void cancelOrders(long orderId) {
        Optional<Orders> orderUpdate = orderRepository.findById(orderId);
        if(orderUpdate.isEmpty()){
            throw new RuntimeException("Order Not Found");
        }
        Orders order = new Orders();
        order.setId(orderId);
        order.setStatus("Cancelled");

        orderRepository.save(order);
    }
}
