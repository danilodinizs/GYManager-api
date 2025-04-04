package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.entity.Workout;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutResponseDTO toDto(Workout entity);

    Workout toEntity(WorkoutRequestDTO dto);

    List<WorkoutResponseDTO> toDtoList(List<Workout> entityList);
    List<Workout> toEntityList(List<WorkoutRequestDTO> dtoList);
}
