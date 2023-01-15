package com.order.service.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "order")
@Getter
@Setter
@Builder
public class Order {
	
	@Transient
    public static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private long id;

	private List<Detail> details;
	
	private long totalOrder;
	
	private boolean status;

}
