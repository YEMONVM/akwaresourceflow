package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String title;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToMany(mappedBy = "tasks")
    private Set<Schedule> schedules;
}
