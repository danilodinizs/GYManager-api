package dev.danilo.gymanager.dto;

import dev.danilo.gymanager.entity.Workout;
import lombok.Builder;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Builder
public record WorkoutResponseDTO(UUID id,
                                 UUID spreadsheetId,
                                 String name,
                                 String description,
                                 DayOfWeek dayOfWeek,
                                 List<Workout> workouts) {

    public WorkoutResponseDTO {
    }
}
