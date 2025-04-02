package dev.danilo.gymanager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SpreadsheetResponseDTO(UUID id,
                                     String name,
                                     String description,
                                     LocalDateTime date,
                                     List<Workout> workouts) {
}
