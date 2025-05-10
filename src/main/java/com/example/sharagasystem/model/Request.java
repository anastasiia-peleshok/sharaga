package com.example.sharagasystem.model;

import com.example.sharagasystem.model.enums.RequestStatus;
import com.example.sharagasystem.model.enums.RequestType;
import com.example.sharagasystem.security.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "requests")
@EqualsAndHashCode(callSuper = true)
public class Request extends AbstractEntity{
    private String number;
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;
    @ManyToOne
    @JoinColumn(name = "resident_id")
    private User resident;
    private String description;
    private RequestType type;
    private RequestStatus status;
}
