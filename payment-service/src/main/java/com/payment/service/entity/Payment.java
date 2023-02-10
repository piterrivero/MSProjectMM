package com.payment.service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
@Getter
@Setter
@Builder
public class Payment {

    @Transient
    public static final String SEQUENCE_NAME = "payment_sequence";

    @Id
    private long id;

    private long idOrder;

    private long totalPayment;

    private boolean status;

    private String statusMessage;

}
