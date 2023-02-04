package com.order.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "details")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
	
	@Transient
    public static final String SEQUENCE_NAME = "details_sequence";
	
	@Id
	private long id;
	
	private int discId;
	
	private int quantity;
	
	private long totalDetail;

}
