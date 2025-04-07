package dev.danilo.gymanager.dto;

import java.util.UUID;

public record ExerciseResponseDTO(UUID id,
                                  String name,
                                  String description,
                                  String sets,
                                  String reps,
                                  String restTime,
                                  String technique,
                                  Integer exerciseOrder,
                                  UUID workoutId) {
}
