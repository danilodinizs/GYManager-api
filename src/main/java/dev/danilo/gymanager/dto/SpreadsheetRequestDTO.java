package dev.danilo.gymanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.danilo.gymanager.entity.Workout;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record SpreadsheetRequestDTO(UUID id,
                                    String name,
                                    String description,
                                    @JsonFormat(pattern = "dd/MM/yyyy")
                                    LocalDate date,
                                    List<Workout> workouts) {

    public SpreadsheetRequestDTO {
    }
}
