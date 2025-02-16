package com.example.sharagasystem.model;

import jakarta.persistence.*;
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
@Table(name = "rooms")
public class Room  extends AbstractEntity{
    @Column(unique = true)
    private String number;
    private int capacity;
    private Gender gender;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ResidentDetails> residents;

    @ManyToOne
    @JoinColumn(name = "dorm_id", nullable = false)
    private Dormitory dormitory;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Furniture> furnitures;

}
