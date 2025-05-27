package com.example.sharagasystem.service;

import com.example.sharagasystem.model.dto.request.payment.PaymentRequestDto;
import com.example.sharagasystem.model.dto.response.payment.PaymentResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);
    List<PaymentResponseDto> getAllPayments();
    PaymentResponseDto getPaymentById(UUID id);
    List<PaymentResponseDto> getPaymentsByUserId(UUID userId);
    List<PaymentResponseDto> getAllPaymentsCreationDate(LocalDate date);
    List<PaymentResponseDto> getAllPaymentsByServiceId(UUID serviceId);
    void checkPendingPayments();
    void notifyForOverduePayments();
    byte[] generatePaymentPdf(UUID id);
}
