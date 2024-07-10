package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;
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

    @OneToMany
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
