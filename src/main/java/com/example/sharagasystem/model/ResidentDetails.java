package com.example.sharagasystem.model;

import com.example.sharagasystem.security.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
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
@Table(name = "resident_details")
@PrimaryKeyJoinColumn
@EqualsAndHashCode(callSuper = true)
public class ResidentDetails extends User {
    private LocalDate dateOfEntry;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Furniture> furnitureList;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Dormitory dormitory;
}
