package com.disc.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiscDTO {

    private long id;

    private int idBand;

    private String title;

    private String year;

    private long price;

    private int stock;

}
