package com.order.service.mapper;

import com.order.service.entity.Order;
import com.order.service.model.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO modelToDto(Order order);

    Order dtoToModel(OrderDTO orderDTO);

    List<OrderDTO> modelsToDto(List<Order> orders);

    List<Order> dtoToModel(List<OrderDTO> orderDTOS);

}
