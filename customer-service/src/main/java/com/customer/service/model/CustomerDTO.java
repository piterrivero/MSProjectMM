package com.customer.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {

    private long id;

    private String name;

    private String surname;

    private long budget;

}
