package com.order.service.model;

import com.order.service.entity.Detail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDTO {

    private long id;

    private List<Detail> details;

    private long totalOrder;

    private boolean status;

    private String statusMessage;

}
