package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.WorkoutRequestDTO;
import dev.danilo.gymanager.dto.WorkoutResponseDTO;
import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.service.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsible for saving a workout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation done successfully!"),
            @ApiResponse(responseCode = "400", description = "Error creating a workout!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<WorkoutResponseDTO> saveWorkout(@RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveWorkout(dto));
    }

    @GetMapping
    @Operation(description = "Endpoint responsible for searching all workouts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workouts found successfully!"),
            @ApiResponse(responseCode = "400", description = "Error searching for all workouts!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<List<WorkoutResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsible for searching a workout by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout found successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Workout not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<WorkoutResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint responsible for deleting a workout by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workout deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Workout not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    @Operation(description = "Endpoint responsible for deleting all workouts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Workouts deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "Error deleting all workouts!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(description = "Endpoint responsible for partially updating a workout by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workout updated successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Workout not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<WorkoutResponseDTO> updateWorkout(@PathVariable UUID id, @RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.ok().body(service.updateWorkout(id, dto));
    }
}
