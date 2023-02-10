package com.order.service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "order")
@Getter
@Setter
@Builder
public class Order {

    @Transient
    public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    private long id;

    private List<Detail> details;

    private long totalOrder;

    private boolean status;

    private String statusMessage;

}
