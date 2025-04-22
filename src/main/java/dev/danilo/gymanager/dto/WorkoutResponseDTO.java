package dev.danilo.gymanager.dto;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public record WorkoutResponseDTO(UUID id,
                                 String name,
                                 String description,
                                 DayOfWeek dayOfWeek,
                                 UUID spreadsheetId,
                                 List<ExerciseResponseDTO> exercises) {
}
