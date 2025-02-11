package com.example.sharagasystem.model;

import com.example.sharagasystem.security.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "furnitures")
public class Furniture extends AbstractEntity {
    private String name;
    private String itemNumber;
    private FurnitureType furnitureType;
    @ManyToOne
    private Room room;
    @ManyToOne
    private UserDetails user;
    @ManyToOne
    private Dormitory dormitory;
}
