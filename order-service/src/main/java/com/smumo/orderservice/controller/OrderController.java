package com.smumo.orderservice.controller;

import com.smumo.orderservice.dto.OrderRequestDTO;
import com.smumo.orderservice.entity.Orders;
import com.smumo.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Objects> placeOrder(Orders order){
        Orders savedOrder = orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(OrderRequestDTO order){
        orderService.placeOrder(order);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{customerId}")
    public List<Orders> getOrders(@PathVariable long customerId){
        return  orderService.getByCustomer(customerId);
    }

    @PutMapping("/cancel/{orderId}")
    public void cancelOrder(@PathVariable long orderId){
        orderService.cancelOrders(orderId);
    }
}
