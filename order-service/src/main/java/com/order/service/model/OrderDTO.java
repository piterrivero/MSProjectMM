package com.order.service.model;

import com.order.service.entity.Detail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long id;

    private List<Detail> details;

    private long totalOrder;

    private boolean status;

    private String statusMessage;

    private long customerId;

}
