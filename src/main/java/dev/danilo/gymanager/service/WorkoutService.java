package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.entity.Workout;
import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import dev.danilo.gymanager.repository.WorkoutRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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
        Spreadsheet spreadsheet = spreadsheetRepository.findById(dto.spreadsheetId())
                .orElseThrow(() -> new RuntimeException("Spreadsheet não encontrado"));

        Workout workout = new Workout();
        workout.setName(dto.name());
        workout.setDescription(dto.description());
        workout.setDayOfWeek(dto.dayOfWeek());
        workout.setSpreadsheet(spreadsheet); // Vinculação manual

        spreadsheet.getWorkouts().add(workout);

        return mapper.toDto(repository.save(workout));
    }

    public List<WorkoutResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public WorkoutResponseDTO findById(UUID id) {

        Optional<Workout> workout = repository.findById(id);

        return workout.map(mapper::toDto).orElse(null);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
