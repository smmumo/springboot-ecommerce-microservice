package com.smumo.customerservice.controller;

import com.smumo.customerservice.dto.CustomerDTO;
import com.smumo.customerservice.entity.Customer;
import com.smumo.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers(){
        return customerService.getCustomerList();
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public ResponseEntity<Objects> createCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.createCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCustomer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/customers/orders/{customerId}")
    public List<Customer> getOrders(@PathVariable  Long customerId){
         return  customerService.getOrders(customerId);
    }


}
