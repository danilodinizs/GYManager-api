package dev.danilo.gymanager.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;
    private String description;

    private String sets;
    private String reps;

    @Column(name = "rest_time")
    private String restTime;

    @Column(name = "exercise_order")
    private Integer exerciseOrder;

    private String technique;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;
}
