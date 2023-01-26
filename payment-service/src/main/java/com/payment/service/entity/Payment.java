package com.payment.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "payment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	@Transient
    public static final String SEQUENCE_NAME = "payment_sequence";
	
	@Id
	private long id;
	
	private long totalPayment;
	
	private boolean status;
	
}
