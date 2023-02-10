package com.customer.service.controller;

import com.customer.service.entity.Customer;
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

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer() {
        log.info("Have been called the listCustomer method");
        List<Customer> customer = customerService.getAll();
        if (customer.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id) {
        log.info("Have been called the getCustomer method");
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        log.info("Have been called the saveCustomer method");
        Customer newCustomer = customerService.save(customer);
        return ResponseEntity.ok(newCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        log.info("Have been called the updateCustomer method");
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        Customer newCustomer = customerService.update(id, customer);
        return ResponseEntity.ok(newCustomer);
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
