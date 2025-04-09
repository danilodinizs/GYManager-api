package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/workout")
public class WorkoutController {

    private final WorkoutService service;

    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> saveWorkout(@RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveWorkout(dto));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutResponseDTO> updateWorkout(@PathVariable UUID id, @RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.ok().body(service.updateWorkout(id, dto));
    }
}
