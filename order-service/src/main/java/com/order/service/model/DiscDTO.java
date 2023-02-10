package com.order.service.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscDTO {

    private long id;

    private int idBand;

    private String title;

    private String year;

    private long price;

    private int stock;

}
