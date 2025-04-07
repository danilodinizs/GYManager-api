package dev.danilo.gymanager.dto;

import dev.danilo.gymanager.entity.Exercise;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import lombok.Builder;

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
