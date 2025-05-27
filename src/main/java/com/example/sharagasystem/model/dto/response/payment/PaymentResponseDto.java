package com.example.sharagasystem.model.dto.response.payment;

import com.example.sharagasystem.model.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentResponseDto {
    private String userName;
    private String serviceName;

    private BigDecimal amount;
    private LocalDate paymentDate;
    private PaymentStatus status;
    private LocalDate creationDate;
    private String paymentLink;
}
