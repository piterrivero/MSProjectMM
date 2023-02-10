package com.customer.service.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private long id;

    private String name;

    private String surname;

    private long budget;

}
