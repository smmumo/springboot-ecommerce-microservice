package com.smumo.orderservice.controller;

import com.smumo.orderservice.dto.OrderRequestDTO;
import com.smumo.orderservice.entity.Orders;
import com.smumo.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Objects> createOrder(Orders order){
        Orders savedOrder = orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PostMapping("/create")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(OrderRequestDTO order){
       return CompletableFuture.supplyAsync( () -> orderService.placeOrder(order))  ;
       // return ResponseEntity.ok("Success");
    }
    public CompletableFuture<String> fallBackMethod(OrderRequestDTO order,RuntimeException runtimeException){
        var msg = "Oops ,Something Went wrong ,Try again";
        return CompletableFuture.supplyAsync( () -> msg);
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
