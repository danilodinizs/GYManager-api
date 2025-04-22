package dev.danilo.gymanager.dto;

import java.time.DayOfWeek;
import java.util.UUID;

public record WorkoutRequestDTO(String name,
                                String description,
                                DayOfWeek dayOfWeek,
                                UUID spreadsheetId) {

}
