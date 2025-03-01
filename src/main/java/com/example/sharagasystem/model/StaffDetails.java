package com.example.sharagasystem.model;

import com.example.sharagasystem.model.enums.StaffType;
import com.example.sharagasystem.security.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff_details")
@EqualsAndHashCode(callSuper = true)
public class StaffDetails extends User {
    @Enumerated(EnumType.STRING)
    private StaffType type;

    @ManyToMany
    private List<Dormitory> dormitory;

//    @ManyToMany
//    private List<CalendarItem> calendarItems;
}
