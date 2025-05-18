package com.example.sharagasystem.service;

import com.example.sharagasystem.model.dto.payment.PaymentRequestDto;
import com.example.sharagasystem.model.Payment;
import com.example.sharagasystem.model.dto.payment.PaymentResponseDto;

public interface PaymentService {
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);

}
