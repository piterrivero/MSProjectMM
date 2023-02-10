package com.customer.service.mapper;

import com.customer.service.entity.Customer;
import com.customer.service.model.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO modelToDto(Customer customer);

    Customer dtoToModel(CustomerDTO customerDTO);

    List<CustomerDTO> modelsToDto(List<Customer> customers);

    List<Customer> dtoToModel(List<CustomerDTO> customerDTOS);

}
