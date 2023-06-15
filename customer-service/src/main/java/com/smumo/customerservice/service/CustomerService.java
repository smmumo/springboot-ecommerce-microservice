package com.smumo.customerservice.service;

import com.smumo.customerservice.dto.CustomerDTO;
import com.smumo.customerservice.dto.CustomerDTOMapper;
import com.smumo.customerservice.entity.Customer;
import com.smumo.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;
    public CustomerService(CustomerRepository customerRepository, CustomerDTOMapper customerDTOMapper) {
        this.customerRepository = customerRepository;
        this.customerDTOMapper = customerDTOMapper;
    }


    public List<CustomerDTO> getCustomerList() {

        return customerRepository.findAll().stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(long id){
       var customer = customerRepository.findById(id)
               .map(customerDTOMapper)
                .orElseThrow( () -> new RuntimeException("customer of id [%s] not found".formatted(id)));
       return customer;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new RuntimeException("Customer Not Found");
        }
        customerRepository.deleteById(id);
    }

    public List<Customer> getOrders(Long customerId) {
        return customerRepository.findAll();
    }
}
