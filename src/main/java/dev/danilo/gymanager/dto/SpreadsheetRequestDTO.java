package dev.danilo.gymanager.dto;

import dev.danilo.gymanager.entity.Workout;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SpreadsheetRequestDTO(UUID id,
                                    String name,
                                    String description,
                                    LocalDateTime date,
                                    List<Workout> workouts) {
}
