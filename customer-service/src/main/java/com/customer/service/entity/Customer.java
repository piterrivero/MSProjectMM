package com.customer.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Transient
    public static final String SEQUENCE_NAME = "customer_sequence";
	
	@Id
	private long id;
	
	private String name;
	
	private String surname;
	
	private long budget;

}
