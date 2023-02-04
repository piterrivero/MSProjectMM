package com.payment.service.model;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private long id;

	private List<DetailDTO> details;
	
	private long totalOrder;
	
	private boolean status;

}
