package dev.danilo.gymanager.dto;

import java.util.UUID;

public record ExerciseRequestDTO(String name,
                                 String description,
                                 String sets,
                                 String reps,
                                 String restTime,
                                 String technique,
                                 Integer exerciseOrder,
                                 UUID workoutId) {
}
