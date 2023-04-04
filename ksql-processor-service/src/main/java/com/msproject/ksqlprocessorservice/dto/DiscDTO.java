package com.msproject.ksqlprocessorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscDTO implements Serializable {

    private UUID id;

    private String title;

    private String year;

    private double price;

    private BandDTO band;

}
