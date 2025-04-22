package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;

import dev.danilo.gymanager.entity.Exercise;
import dev.danilo.gymanager.entity.Workout;
import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.repository.ExerciseRepository;
import dev.danilo.gymanager.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
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
                .orElseThrow(() -> new RuntimeException("Workout não encontrado"));

        log.info("Workout to save this exercise found with id: " + dto.workoutId());

        Exercise exercise = new Exercise();
        exercise.setName(dto.name());
        exercise.setDescription(dto.description());
        exercise.setName(dto.name());
        exercise.setDescription(dto.description());
        exercise.setSets(dto.sets());
        exercise.setReps(dto.reps());
        exercise.setRestTime(dto.restTime());
        exercise.setTechnique(dto.technique());
        exercise.setExerciseOrder(dto.exerciseOrder());
        exercise.setWorkout(workout);

        workout.getExercises().add(exercise);

        log.info("Exercise saved and added to workout: " + exercise.toString());

        return mapper.toDto(repository.save(exercise));
    }

    public List<ExerciseResponseDTO> findAll() {
        log.info("Looking for all exercises");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ExerciseResponseDTO findById(UUID id) {
        Optional<Exercise> exercise = repository.findById(id);
        log.info("Exercise found by id: " + id + " or returning null");
        return exercise.map(mapper::toDto).orElseThrow(null); // exception here
    }

    public void deleteById(UUID id) {
        if(!repository.existsById(id)) {
            // exception here: throw new Exception("Not found ")
        }
        log.info("Deleting exercise by id: " + id);
        repository.deleteById(id);
    }

    public ExerciseResponseDTO updateExercise(UUID id, ExerciseRequestDTO dto) {

        log.info("Starting the process of updating an Exercise");

        Optional<Exercise> exercise = repository.findById(id) ; // exception here .orElseThrow(() -> new )

        log.info("Exercise found by id: " + id);

        if(exercise.isPresent()) {
            Exercise newExercise = exercise.get();

            Workout workout = new Workout();
            workout.setId(dto.workoutId());

            newExercise.setName(dto.name());
            newExercise.setDescription(dto.description());
            newExercise.setSets(dto.sets());
            newExercise.setReps(dto.reps());
            newExercise.setRestTime(dto.restTime());
            newExercise.setExerciseOrder(dto.exerciseOrder());
            newExercise.setTechnique(dto.technique());
            newExercise.setWorkout(workout);

            log.info("Saving new exercise: " + newExercise.toString());

            return mapper.toDto(repository.save(newExercise));
        }
        return null;
    }

    public void deleteAll() {

        log.info("Deleting all exercises");

        repository.deleteAll();
    }
}
