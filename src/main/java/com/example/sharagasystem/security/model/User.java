package com.example.sharagasystem.security.model;

import com.example.sharagasystem.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
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
