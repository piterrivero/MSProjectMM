package com.order.service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Detail {

    private int discId;

    private int quantity;

    private long totalDetail;

}
