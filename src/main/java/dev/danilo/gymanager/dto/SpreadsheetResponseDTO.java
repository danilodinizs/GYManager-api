package dev.danilo.gymanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SpreadsheetResponseDTO(UUID id,
                                     String name,
                                     String description,
                                     @JsonFormat(pattern = "dd/MM/yyyy")
                                     LocalDate date,
                                     List<WorkoutResponseDTO> workouts) {
}
