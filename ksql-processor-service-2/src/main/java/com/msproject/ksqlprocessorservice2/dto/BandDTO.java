package com.msproject.ksqlprocessorservice2.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BandDTO implements Serializable {

    private UUID id;

    private String name;

    private String country;

    private GenreDTO genre;

}
