package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;

import dev.danilo.gymanager.entity.Exercise;
import dev.danilo.gymanager.entity.Workout;
import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.repository.ExerciseRepository;
import dev.danilo.gymanager.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
public class ExerciseService {

    private final ExerciseRepository repository;
    private final ExerciseMapper mapper;
    private final WorkoutRepository workoutRepository;

    public ExerciseService(ExerciseRepository repository, ExerciseMapper mapper, WorkoutRepository workoutRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.workoutRepository = workoutRepository;
    }


    @Transactional
        public ExerciseResponseDTO saveExercise(ExerciseRequestDTO dto) {

        log.info("Starting the process of saving an Exercise");

        Workout workout = workoutRepository.findById(dto.workoutId())
                .orElseThrow(() -> new EntityNotFoundException("Workout not found"));

        log.info("Workout to save this exercise found with id: {}", dto.workoutId());

        Exercise exercise = new Exercise();
        exercise.setName(dto.name());
        exercise.setDescription(dto.description());
        exercise.setSets(dto.sets());
        exercise.setReps(dto.reps());
        exercise.setRestTime(dto.restTime());
        exercise.setTechnique(dto.technique());
        exercise.setExerciseOrder(dto.exerciseOrder());
        exercise.setWorkout(workout);

        workout.getExercises().add(exercise);

        log.info("Exercise saved and added to workout: {}", exercise.toString());

        return mapper.toDto(repository.save(exercise));
    }

    public List<ExerciseResponseDTO> findAll() {
        log.info("Looking for all exercises");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ExerciseResponseDTO findById(UUID id) {
        log.info("Searching exercise by id: {}", id);
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Exercise not found with ID: " + id));
    }

    public void deleteById(UUID id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Exercise not found with ID: " + id);
        }
        log.info("Deleting exercise by id: {}", id);
        repository.deleteById(id);
    }

    public ExerciseResponseDTO updateExercise(UUID id, @Valid ExerciseRequestDTO dto) {

        log.info("Starting the process of updating an Exercise");

        Exercise exercise = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Exercise not found with ID: " + id));

        log.info("Exercise found by id: {}", id);


        Workout workout = workoutRepository.findById(dto.workoutId())
                .orElseThrow(() -> new EntityNotFoundException("Workout not found with ID: " + dto.workoutId()));

        exercise.setName(dto.name());
        exercise.setDescription(dto.description());
        exercise.setSets(dto.sets());
        exercise.setReps(dto.reps());
        exercise.setRestTime(dto.restTime());
        exercise.setExerciseOrder(dto.exerciseOrder());
        exercise.setTechnique(dto.technique());
        exercise.setWorkout(workout);

        log.info("Saving new exercise: {}", exercise.toString());

        return mapper.toDto(repository.save(exercise));

    }


    public void deleteAll() {

        log.info("Deleting all exercises");

        repository.deleteAll();
    }
}
