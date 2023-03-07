package com.msproject.relationaldb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stock")
@ToString
@Getter
@Setter
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "stock")
    private int stock;

    @ToString.Exclude
    @ManyToOne
    private Disc disc;

}
