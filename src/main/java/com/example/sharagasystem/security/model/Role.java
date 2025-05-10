package com.example.sharagasystem.security.model;

import com.example.sharagasystem.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName roleName;

    public enum RoleName{
        ADMIN, RESIDENT, COMENDA, EMPLOYEE, VAHTER, OPER, DIRECTOR
    }
}
