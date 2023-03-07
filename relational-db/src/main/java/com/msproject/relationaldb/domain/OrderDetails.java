package com.msproject.relationaldb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "order_details")
@ToString
@Getter
@Setter
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_detail")
    private double totalDetail;

    @ToString.Exclude
    @ManyToOne
    private Disc disc;

    @ToString.Exclude
    @ManyToOne
    private Order order;

}

