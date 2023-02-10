package com.disc.service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "disc")
@Getter
@Setter
@Builder
public class Disc {

    @Transient
    public static final String SEQUENCE_NAME = "disc_sequence";

    @Id
    private long id;

    private int idBand;

    private String title;

    private String year;

    private long price;

    private int stock;

}
