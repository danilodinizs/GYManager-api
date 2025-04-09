package dev.danilo.gymanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "spreadsheet")
public class Spreadsheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;

    private String description;

    @Column(name = "created_at")
    private LocalDate date;

    @OneToMany(mappedBy = "spreadsheet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Workout> workouts = new ArrayList<>();

}
