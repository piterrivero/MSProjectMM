package com.payment.service.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailDTO {
	
	private long id;
	
	private int discId;
	
	private int quantity;
	
	private long totalDetail;

}
