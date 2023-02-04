package com.order.service.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscDTO {

    private long id;

    private int idBand;

    private String title;

    private String year;

    private long price;

    private int stock;

}
