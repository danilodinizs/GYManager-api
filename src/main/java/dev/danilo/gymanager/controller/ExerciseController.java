package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;
import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/exercise")
public class ExerciseController {

    private final ExerciseService service;

    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> saveExercise(@RequestBody ExerciseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveExercise(dto));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDTO> findById(@PathVariable UUID id) {
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
    public ResponseEntity<ExerciseResponseDTO> updateExercise(@PathVariable UUID id, @RequestBody ExerciseRequestDTO dto) {
        return ResponseEntity.ok().body(service.updateExercise(id, dto));
    }

}
