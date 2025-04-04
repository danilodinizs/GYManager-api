package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkoutMapper {
    public WorkoutResponseDTO toDto(Workout entity) {
        return new WorkoutResponseDTO(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDayOfWeek(),
                entity.getSpreadsheet().getId())/*,
                entity.getExercises())*/;
    }

    public Workout toEntity(WorkoutRequestDTO dto) {
        Workout workout = new Workout();
        workout.setName(dto.name());
        workout.setDescription(dto.description());
        workout.setDayOfWeek(dto.dayOfWeek());

        if (dto.spreadsheetId() != null) {
            Spreadsheet spreadsheet = new Spreadsheet();
            spreadsheet.setId(dto.spreadsheetId());
            workout.setId(spreadsheet.getId());
        }
        return workout;
    }

    public List<WorkoutResponseDTO> toDtoList(List<Workout> entityList){
        if (entityList == null) {
            return Collections.emptyList();
        }

        return entityList.stream()
                .map(this::toDto)  // Reutiliza o método toDto individual
                .collect(Collectors.toList());
    }

    public List<Workout> toEntityList(List<WorkoutRequestDTO> dtoList){
        return null;
    }
}
