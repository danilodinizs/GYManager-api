package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import dev.danilo.gymanager.repository.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;
    private final SpreadsheetRepository spreadsheetRepository;

    public WorkoutService(WorkoutRepository repository, WorkoutMapper mapper, SpreadsheetRepository spreadsheetRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.spreadsheetRepository = spreadsheetRepository;
    }

    @Transactional
    public WorkoutResponseDTO saveWorkout(WorkoutRequestDTO dto) {
        log.info("Starting the process of saving a Workout");

        Spreadsheet spreadsheet = spreadsheetRepository.findById(dto.spreadsheetId())
                .orElseThrow(() -> new EntityNotFoundException("Spreadsheet not found by id: " + dto.spreadsheetId()));

        log.info("Spreadsheet to save this workout found with id: {}", dto.spreadsheetId());

        Workout workout = new Workout();
        workout.setName(dto.name());
        workout.setDescription(dto.description());
        workout.setDayOfWeek(dto.dayOfWeek());
        workout.setSpreadsheet(spreadsheet);

        spreadsheet.getWorkouts().add(workout);

        log.info("Workout saved and added to spreadsheet: {}", workout.toString());

        return mapper.toDto(repository.save(workout));
    }

    public List<WorkoutResponseDTO> findAll() {
        log.info("Looking for all workouts");

        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public WorkoutResponseDTO findById(UUID id) {
        log.info("Searching workout by id: {}", id);
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new EntityNotFoundException("Workout not found by id: " + id)); // exception here
    }

    public void deleteById(UUID id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Workout not found by id: " + id);
        }

        log.info("Deleting workout by id: {}", id);
        repository.deleteById(id);
    }

    public WorkoutResponseDTO updateWorkout(UUID id, WorkoutRequestDTO dto) {

        log.info("Starting the process of updating a Workout");

        Workout workout = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Workout not found by id: " + id));

        log.info("Workout found by id: {}", id);

        Spreadsheet spreadsheet = spreadsheetRepository.findById(dto.spreadsheetId()).orElseThrow(() -> new EntityNotFoundException("Spreadsheet not found by id: " + dto.spreadsheetId()));

        workout.setName(dto.name());
        workout.setName(dto.name());
        workout.setDescription(dto.description());
        workout.setDayOfWeek(dto.dayOfWeek());
        workout.setSpreadsheet(spreadsheet);

        log.info("Saving new workout: {}", workout.toString());

        return mapper.toDto(repository.save(workout));

    }


    public void deleteAll() {

        log.info("Deleting all workouts");

        repository.deleteAll();
    }
}
