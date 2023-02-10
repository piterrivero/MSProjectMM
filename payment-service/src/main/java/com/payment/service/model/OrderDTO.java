package com.payment.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {

    private long id;

    private List<DetailDTO> details;

    private long totalOrder;

    private boolean status;

    private String statusMessage;

}
