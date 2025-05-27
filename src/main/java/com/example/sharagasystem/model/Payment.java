package com.example.sharagasystem.model;

import com.example.sharagasystem.model.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment extends AbstractEntity {
    @ManyToOne
    private ResidentDetails resident;

    @ManyToOne
    private Service service;

    private BigDecimal amount;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    private PaymentStatus status;
    @Column(name = "creation_date")
    private LocalDate creationDate;

}
