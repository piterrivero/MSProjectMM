package com.msproject.relationaldb.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscDTO implements Serializable {

    private UUID id;

    private String title;

    private String year;

    private double price;

    private BandDTO band;

}
