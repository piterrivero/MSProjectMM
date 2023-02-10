package com.customer.service.controller;

import com.customer.service.entity.Customer;
import com.customer.service.mapper.CustomerMapper;
import com.customer.service.model.CustomerDTO;
import com.customer.service.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> listCustomer() {
        log.info("Have been called the listCustomer method");
        List<Customer> customer = customerService.getAll();
        if (customer.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customerMapper.modelsToDto(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") int id) {
        log.info("Have been called the getCustomer method");
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerMapper.modelToDto(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customer) {
        log.info("Have been called the saveCustomer method");
        Customer newCustomer = customerService.save(customerMapper.dtoToModel(customer));
        return ResponseEntity.ok(customerMapper.modelToDto(newCustomer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO customer) {
        log.info("Have been called the updateCustomer method");
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        Customer newCustomer = customerService.update(id, customerMapper.dtoToModel(customer));
        return ResponseEntity.ok(customerMapper.modelToDto(newCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
        log.info("Have been called the deleteCustomer method");
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerService.delete(id);
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

}
