package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Disc {
	
	private int idBand;

	private String title;
	
	private String year;

}
