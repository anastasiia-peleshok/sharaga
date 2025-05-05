package com.example.sharagasystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dorms")
public class Dormitory extends AbstractEntity {
    private String address;
    private String name;
    private int capacity;
    private Double price;
    private Double countOfFloors;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<ResidentDetails> staff;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<ResidentDetails> residents;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Room> rooms;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<Furniture> furnitures;


}
