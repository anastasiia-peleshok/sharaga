package com.example.sharagasystem.security.model;

import com.example.sharagasystem.model.AbstractEntity;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Furniture;
import com.example.sharagasystem.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity {
    private int id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private String firstName;
    private String lastName;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private String phoneNumber;

//    private List<Notfication> notfications = new ArrayList<>();

}
