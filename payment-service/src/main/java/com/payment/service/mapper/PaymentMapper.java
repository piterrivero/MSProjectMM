package com.payment.service.mapper;

import com.payment.service.entity.Payment;
import com.payment.service.model.PaymentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO modelToDto(Payment payment);

    Payment dtoToModel(PaymentDTO paymentDTO);

    List<PaymentDTO> modelsToDto(List<Payment> payments);

    List<Payment> dtoToModel(List<PaymentDTO> paymentDTOS);

}
