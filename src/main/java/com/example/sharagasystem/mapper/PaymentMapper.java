package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Payment;
import com.example.sharagasystem.model.Service;
import com.example.sharagasystem.model.dto.request.payment.PaymentRequestDto;
import com.example.sharagasystem.model.dto.request.service.ServiceCreationRequestDto;
import com.example.sharagasystem.model.dto.response.payment.PaymentResponseDto;
import com.example.sharagasystem.model.dto.response.service.ServiceResponseDto;
import com.example.sharagasystem.model.enums.PaymentStatus;
import com.example.sharagasystem.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PaymentMapper {

    public static Payment mapToPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setStatus(PaymentStatus.UNPAID);
        payment.setCreationDate(LocalDate.now());
        return payment;
    }

    public static PaymentResponseDto mapToPaymentResponse (Payment payment) {
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        paymentResponseDto.setAmount(payment.getAmount());
        if(payment.getPaymentDate() != null){
            paymentResponseDto.setPaymentDate(payment.getPaymentDate());
        }
        paymentResponseDto.setStatus(payment.getStatus());
        paymentResponseDto.setCreationDate(payment.getCreationDate());
        paymentResponseDto.setUserName(payment.getResident().getFirstName() + " " + payment.getResident().getLastName());
        paymentResponseDto.setServiceName(payment.getService().getName());
        return paymentResponseDto;
    }
}
