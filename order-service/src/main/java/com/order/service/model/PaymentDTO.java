package com.order.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentDTO {
	
	private long id;
	
	private long totalPayment;
	
	private boolean status;
	
}
