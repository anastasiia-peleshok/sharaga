package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.mapper.PaymentMapper;
import com.example.sharagasystem.model.dto.payment.PaymentRequestDto;
import com.example.sharagasystem.model.Payment;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.dto.payment.PaymentResponseDto;
import com.example.sharagasystem.model.enums.PaymentStatus;
import com.example.sharagasystem.repository.PaymentRepository;
import com.example.sharagasystem.service.PaymentService;
import com.example.sharagasystem.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ResidentService residentService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        Payment payment = new Payment();
        ResidentDetails resident = residentService.findById(paymentRequestDto.getUserId());
        payment.setUser(resident);
        resident.getPayments().add(payment);
        payment.setPrice(paymentRequestDto.getPrice());
        payment.setServiceType(paymentRequestDto.getServiceType());
        payment.setStatus(PaymentStatus.PENDING);
        return paymentMapper.toPaymentResponseDto(paymentRepository.save(payment));

    }
}
