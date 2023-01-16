package com.payment.service.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Order {
	
	private long id;

	private List<Detail> details;
	
	private long totalOrder;
	
	private boolean status;

}
