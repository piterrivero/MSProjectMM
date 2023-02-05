package com.order.service.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private long id;

	private long idOrder;
	
	private long totalPayment;
	
	private boolean status;

	private String statusMessage;
	
}
