package com.example.sharagasystem.model;

import com.example.sharagasystem.model.enums.PaymentStatus;
import com.example.sharagasystem.model.enums.ServiceType;
import com.example.sharagasystem.security.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
@EqualsAndHashCode(callSuper = true)
public class Payment extends AbstractEntity{
    @ManyToOne
    private ResidentDetails user;

    private Double price;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
