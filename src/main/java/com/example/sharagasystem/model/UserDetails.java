package com.example.sharagasystem.model;

import com.example.sharagasystem.security.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "user_details")
@PrimaryKeyJoinColumn
public class UserDetails extends User {
    private String catName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Furniture> furnitureList;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Dormitory dormitory;
}
