package com.msproject.relationaldb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "[order]")
@ToString
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "total_order")
    private double totalOrder;

    @Column(name = "status")
    private boolean status;

    @Column(name = "status_message")
    private String statusMessage;

    @ToString.Exclude
    @ManyToOne
    private Customer customer;

    @ToString.Exclude
    @OneToMany
    private List<OrderDetails> orderDetails;

}
