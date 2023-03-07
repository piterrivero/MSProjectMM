package com.msproject.relationaldb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payment")
@ToString
@Getter
@Setter
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "total_payment")
    private double totalPayment;

    @Column(name = "status")
    private boolean status;

    @Column(name = "status_message")
    private String statusMessage;

    @ToString.Exclude
    @OneToOne
    private Order order;

}
