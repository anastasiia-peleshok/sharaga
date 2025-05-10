package com.example.sharagasystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="project_number")
public class ProjectNumber {
    @Id
    private String type;
    @Column(name = "last_number")
    private Long lastNumber;
}
