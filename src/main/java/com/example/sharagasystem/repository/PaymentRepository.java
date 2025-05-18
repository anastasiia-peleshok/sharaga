package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    //поки ніц
}
