package com.band.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "band")
@Getter
@Setter
public class Band {

	@Transient
    public static final String SEQUENCE_NAME = "band_sequence";
	
	@Id
	private long id;
	
	private int idGenre;
	
	private String name;
	
	private String country;
	
}
