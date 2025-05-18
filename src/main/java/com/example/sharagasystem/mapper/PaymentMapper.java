package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Payment;
import com.example.sharagasystem.model.dto.payment.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PaymentMapper {
    private final ResidentDetailsMapper residentDetailsMapper;
    public PaymentResponseDto toPaymentResponseDto(Payment payment) {
        if(payment == null) {
            return null;
        }
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setId(payment.getId());
        paymentResponseDto.setDate(payment.getCreatedAt().toString());
        paymentResponseDto.setStatus(payment.getStatus());
        paymentResponseDto.setPrice(payment.getPrice());
        paymentResponseDto.setUser(residentDetailsMapper.mapToResidentDetailsLowInfoResponseDto(payment.getUser()));
        paymentResponseDto.setServiceType(payment.getServiceType());
        return paymentResponseDto;
    }
}
