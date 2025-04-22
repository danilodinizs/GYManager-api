package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import dev.danilo.gymanager.repository.WorkoutRepository;
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
                .orElseThrow(() -> new RuntimeException("Spreadsheet não encontrado"));


        log.info("Spreadsheet to save this workout found with id: " + dto.spreadsheetId());

        Workout workout = new Workout();
        workout.setName(dto.name());
        workout.setDescription(dto.description());
        workout.setDayOfWeek(dto.dayOfWeek());
        workout.setSpreadsheet(spreadsheet);

        spreadsheet.getWorkouts().add(workout);

        log.info("Workout saved and added to spreadsheet: " + workout.toString());

        return mapper.toDto(repository.save(workout));
    }

    public List<WorkoutResponseDTO> findAll() {
        log.info("Looking for all workouts");

        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public WorkoutResponseDTO findById(UUID id) {
        Optional<Workout> workout = repository.findById(id);
        log.info("Workout found by id: " + id + " or returning null");
        return workout.map(mapper::toDto).orElseThrow(null); // exception here
    }

    public void deleteById(UUID id) {
        if(!repository.existsById(id)) {
            // exception here: throw new Exception("Not found ")
        }

        log.info("Deleting workout by id: " + id);
        repository.deleteById(id);
    }

    public WorkoutResponseDTO updateWorkout(UUID id, WorkoutRequestDTO dto) {

        log.info("Starting the process of updating a Workout");

        Optional<Workout> workout = repository.findById(id); // exception here .orElseThrow(() -> new )

        log.info("Workout found by id: " + id);

        if(workout.isPresent()) {
            Workout newWorkout = workout.get();

            Spreadsheet spreadsheet = new Spreadsheet();
            spreadsheet.setId(dto.spreadsheetId());

            newWorkout.setName(dto.name());
            newWorkout.setName(dto.name());
            newWorkout.setDescription(dto.description());
            newWorkout.setDayOfWeek(dto.dayOfWeek());
            newWorkout.setSpreadsheet(spreadsheet);

            log.info("Saving new workout: " + workout.toString());

            return mapper.toDto(repository.save(newWorkout));
        }
        return null;
    }

    public void deleteAll() {

        log.info("Deleting all workouts");

        repository.deleteAll();
    }
}
