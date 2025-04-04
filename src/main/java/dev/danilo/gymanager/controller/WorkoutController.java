package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService service;

    private final WorkoutMapper mapper;


    public WorkoutController(WorkoutService service, WorkoutMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> saveWorkout(@RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveWorkout(dto));
    }
}
