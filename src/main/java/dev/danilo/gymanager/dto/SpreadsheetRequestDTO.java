package dev.danilo.gymanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record SpreadsheetRequestDTO(String name,
                                    String description,
                                    @JsonFormat(pattern = "dd/MM/yyyy")
                                    LocalDate date) {
}
