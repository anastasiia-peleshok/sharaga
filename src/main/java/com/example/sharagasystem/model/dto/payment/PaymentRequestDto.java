package com.example.sharagasystem.model.dto.payment;


import com.example.sharagasystem.model.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentRequestDto {
    private UUID userId;
    private UUID dormitoryId;
    private ServiceType serviceType;
    private Double price;
    private String date;
}
