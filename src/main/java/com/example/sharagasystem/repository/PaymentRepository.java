package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Payment;
import com.example.sharagasystem.model.dto.response.payment.PaymentResponseDto;
import com.example.sharagasystem.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.jar.JarFile;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    @Query("SELECT p FROM Payment p WHERE p.creationDate = :creationDate")
    List<Payment> findByCreationDate(@Param("creationDate") LocalDate creationDate);

    @Query("SELECT p FROM Payment p WHERE p.resident.id = :residentId")
    List<Payment> findByResidentId(@Param("residentId") UUID residentId);

    @Query("SELECT p FROM Payment p WHERE p.service.id = :serviceId")
    List<Payment> findByServiceId(@Param("serviceId") UUID serviceId);

    @Query("SELECT p FROM Payment p WHERE p.status = :status")
    List<Payment> findByStatus(@Param("status")PaymentStatus status);
}
