package com.payment.service.feignclients;


import com.payment.service.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service")
@RequestMapping("/customer")
public interface CustomerFeignClient {

    @GetMapping("/{id}")
    CustomerDTO getCustomer(@PathVariable("id") long id);

    @PutMapping("/{id}")
    CustomerDTO updateCustomer(@PathVariable("id") long id, CustomerDTO customerDTO);

}
