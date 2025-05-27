package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.request.payment.PaymentRequestDto;
import com.example.sharagasystem.model.dto.response.payment.PaymentResponseDto;
import com.example.sharagasystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public PaymentResponseDto createPayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        return paymentService.createPayment(paymentRequestDto);
    }

    @GetMapping("/{paymentId}")
    public PaymentResponseDto getPayment(@PathVariable UUID paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadOrderPdf(@PathVariable UUID id) {

        try {
            byte[] pdfBytes = paymentService.generatePaymentPdf(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename("payment_" + id + ".pdf").build());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
