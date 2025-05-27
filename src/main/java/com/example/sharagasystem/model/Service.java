package com.example.sharagasystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
@Getter
@Setter
public class Service extends AbstractEntity {
    @Column(unique = true)
    private String name;
    @Column(name = "monthly_rate")
    private BigDecimal monthlyRate;

}