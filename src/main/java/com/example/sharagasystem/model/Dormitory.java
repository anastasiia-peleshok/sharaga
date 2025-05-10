package com.example.sharagasystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dorms")
@EqualsAndHashCode(callSuper = true)
public class Dormitory extends AbstractEntity {
    private String address;
    private String zipCode;
    private String name;
    private Integer capacity;
    private Double price;
    private Integer floors;
    private String city;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    private List<StaffDetails> staff;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<ResidentDetails> residents;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Room> rooms;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<Furniture> furnitures;


}
