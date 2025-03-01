package com.example.sharagasystem.model;

import com.example.sharagasystem.model.enums.StatusType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "furnitures")
@EqualsAndHashCode(callSuper = true)
public class Furniture extends AbstractEntity {
    private String name;
    private String itemNumber;
    private Double price;
    @Enumerated(EnumType.STRING)
    private StatusType status;
    @Enumerated(EnumType.STRING)
    private FurnitureType type;
    @ManyToOne
    private Room room;
    @ManyToOne
    private ResidentDetails user;
    @ManyToOne
    private Dormitory dormitory;
}
