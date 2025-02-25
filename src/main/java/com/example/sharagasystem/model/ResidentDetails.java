package com.example.sharagasystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "resident_details")
@PrimaryKeyJoinColumn
public class ResidentDetails extends User {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Furniture> furnitureList;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Dormitory dormitory;
}
