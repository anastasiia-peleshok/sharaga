package com.example.sharagasystem.model.dto.request.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PaymentRequestDto {
    private UUID residentId;
    private UUID serviceId;
    private BigDecimal amount;
}
