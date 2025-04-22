package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.ExerciseRequestDTO;
import dev.danilo.gymanager.dto.ExerciseResponseDTO;
import dev.danilo.gymanager.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
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
    @Operation(description = "Endpoint responsible for saving an exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation done successfully!"),
            @ApiResponse(responseCode = "400", description = "Error creating an exercise!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<ExerciseResponseDTO> saveExercise(@Valid @RequestBody ExerciseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveExercise(dto));
    }

    @GetMapping
    @Operation(description = "Endpoint responsible for searching all exercises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercises found successfully!"),
            @ApiResponse(responseCode = "400", description = "Error searching for all exercises!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<List<ExerciseResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsible for searching an exercise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise found successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Exercise not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<ExerciseResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint responsible for deleting an exercise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercise deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Exercise not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    @Operation(description = "Endpoint responsible for deleting all exercises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exercises deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "Error deleting all exercises!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(description = "Endpoint responsible for partially updating an exercise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercise updated successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Exercise not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<ExerciseResponseDTO> updateExercise(@PathVariable UUID id, @Valid @RequestBody ExerciseRequestDTO dto) {
        return ResponseEntity.ok().body(service.updateExercise(id, dto));
    }

}
