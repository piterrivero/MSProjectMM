package com.genre.service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genre")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Transient
    public static final String SEQUENCE_NAME = "genre_sequence";

    @Id
    private long id;

    private String genre;

}
