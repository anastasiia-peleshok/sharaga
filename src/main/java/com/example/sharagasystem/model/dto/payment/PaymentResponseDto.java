package com.example.sharagasystem.model.dto.payment;


import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.dto.response.DormitoryResponseDto;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.ResidentResponseDto;
import com.example.sharagasystem.model.enums.PaymentStatus;
import com.example.sharagasystem.model.enums.ServiceType;
import com.example.sharagasystem.security.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentResponseDto {
    private UUID id;
    private ResidentDetailsLowInfoResponseDto user;
    private ServiceType serviceType;
    private Double price;
    private String date;
    private PaymentStatus status;
}