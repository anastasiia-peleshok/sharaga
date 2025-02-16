package com.example.sharagasystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dorms")
public class Dormitory extends AbstractEntity {
    private String address;
    private String name;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<ResidentDetails> staff;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<ResidentDetails> residents;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<Furniture> furnitures;


}
