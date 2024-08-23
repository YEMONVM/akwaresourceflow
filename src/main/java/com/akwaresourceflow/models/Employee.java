package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String position;
    private String email; // Added
    private String phone; // Added
    @Temporal(TemporalType.DATE)
    private Date hireDate; // Added

    @OneToMany
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @OneToOne
    @JoinColumn(name = "appuser_id")
    private AppUser appuser;
}
