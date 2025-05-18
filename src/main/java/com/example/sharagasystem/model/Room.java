package com.example.sharagasystem.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "rooms")
@EqualsAndHashCode(callSuper = true)
public class Room extends AbstractEntity{
    @Column(unique = true)
    private String number;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer free;
    private Integer occupied;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ResidentDetails> residents;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "dorm_id")
    private Dormitory dormitory;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Furniture> furnitures;

//    @OneToOne
//    private CalendarItem calendarItem;

}
