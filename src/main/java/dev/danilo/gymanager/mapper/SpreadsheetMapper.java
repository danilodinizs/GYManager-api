package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpreadsheetMapper {

    private final WorkoutMapper workoutMapper;

    public SpreadsheetMapper(WorkoutMapper workoutMapper) {
        this.workoutMapper = workoutMapper;
    }


    public SpreadsheetResponseDTO toDto(Spreadsheet spreadsheet) {
        return new SpreadsheetResponseDTO(spreadsheet.getId(),
                spreadsheet.getName(), spreadsheet.getDescription(),
                spreadsheet.getDate(),
                workoutMapper.toDtoList(spreadsheet.getWorkouts()));
    }

    public Spreadsheet toEntity(SpreadsheetRequestDTO dto) {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setName(dto.name());
        spreadsheet.setDescription(dto.description());
        spreadsheet.setDate(dto.date());
        spreadsheet.setWorkouts(new ArrayList<>());
        return spreadsheet;
    }

    public List<SpreadsheetResponseDTO> toDtoList(List<Spreadsheet> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }

        return entityList.stream()
                .map(this::toDto)  // Reutiliza o método toDto individual
                .collect(Collectors.toList());
    }
    public List<Spreadsheet> toEntityList(List<SpreadsheetRequestDTO> spreadsheetList) {
        return null;
    }
}
