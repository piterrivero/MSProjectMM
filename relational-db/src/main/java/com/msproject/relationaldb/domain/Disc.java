package com.msproject.relationaldb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "disc")
@ToString
@Getter
@Setter
public class Disc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private String year;

    @Column(name = "price")
    private double price;

    @ToString.Exclude
    @ManyToOne
    private Band band;

    @ToString.Exclude
    @OneToMany
    private List<OrderDetails> orderDetails;

    @ToString.Exclude
    @OneToMany
    private List<Stock> stocks;

}
