package com.msproject.relationaldb.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BandDTO implements Serializable {

    private UUID id;

    private String name;

    private String country;

    private GenreDTO genre;

}
