package com.band.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiscDTO {
	
	private int idBand;

	private String title;
	
	private String year;

}
