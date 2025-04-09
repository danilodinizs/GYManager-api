package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;
import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.entity.Exercise;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.repository.ExerciseRepository;
import dev.danilo.gymanager.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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
        Workout workout = workoutRepository.findById(dto.workoutId())
                .orElseThrow(() -> new RuntimeException("Workout não encontrado"));

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

        return mapper.toDto(repository.save(exercise));
    }

    public List<ExerciseResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public ExerciseResponseDTO findById(UUID id) {
        Optional<Exercise> exercise = repository.findById(id);
        return exercise.map(mapper::toDto).orElse(null);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public ExerciseResponseDTO updateExercise(UUID id, ExerciseRequestDTO dto) {
        Optional<Exercise> exercise = repository.findById(id);



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

            return mapper.toDto(repository.save(newExercise));
        }
        return null;
    }
}
