package com.payment.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Detail {
	
	private long id;
	
	private int orderId;
	
	private int discId;
	
	private int quantity;
	
	private long totalDetail;

}
