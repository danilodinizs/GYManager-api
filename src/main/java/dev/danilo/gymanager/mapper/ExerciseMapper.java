package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;
import dev.danilo.gymanager.entity.Exercise;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public ExerciseResponseDTO toDto(Exercise entity) {
        return new ExerciseResponseDTO(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getSets(),
                entity.getReps(),
                entity.getRestTime(),
                entity.getTechnique(),
                entity.getExerciseOrder(),
                entity.getWorkout().getId());
    }

    public Exercise toEntity(ExerciseRequestDTO dto) {
        Exercise exercise = new Exercise();
        exercise.setName(dto.name());
        exercise.setDescription(dto.description());
        exercise.setSets(dto.sets());
        exercise.setReps(dto.reps());
        exercise.setRestTime(dto.restTime());
        exercise.setTechnique(dto.technique());
        exercise.setExerciseOrder(dto.exerciseOrder());

        if (dto.workoutId() != null) {
            Workout workout = new Workout();
            workout.setId(dto.workoutId());
            exercise.setId(workout.getId());
        }
        return exercise;
    }
}
