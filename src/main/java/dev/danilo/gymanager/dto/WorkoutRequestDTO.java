package dev.danilo.gymanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record WorkoutRequestDTO(String name,
                                String description,
                                DayOfWeek dayOfWeek,
                                UUID spreadsheetId) {

}
