package com.payment.service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long id;

    private List<DetailDTO> details;

    private long totalOrder;

    private boolean status;

    private String statusMessage;

}
