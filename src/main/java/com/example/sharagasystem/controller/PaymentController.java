package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.payment.PaymentRequestDto;
import com.example.sharagasystem.model.dto.payment.PaymentResponseDto;
import com.example.sharagasystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController  {
    private final PaymentService paymentService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PaymentResponseDto createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.createPayment(paymentRequestDto);
    }

}
