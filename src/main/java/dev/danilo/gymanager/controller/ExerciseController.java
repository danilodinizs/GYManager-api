package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;
import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService service;
    private final ExerciseMapper mapper;


    public ExerciseController(ExerciseService service, ExerciseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ExerciseResponseDTO> saveExercise(@RequestBody ExerciseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveExercise(dto));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
