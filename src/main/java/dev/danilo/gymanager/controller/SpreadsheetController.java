package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.service.SpreadsheetService;
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
@RequestMapping("/v1/spreadsheet")
public class SpreadsheetController {

    private final SpreadsheetService service;

    public SpreadsheetController(SpreadsheetService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Endpoint responsible for saving a spreadsheet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creation done successfully!"),
            @ApiResponse(responseCode = "400", description = "Error creating a spreadsheet!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<SpreadsheetResponseDTO> saveSpreadsheet(@RequestBody @Valid SpreadsheetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    @Operation(description = "Endpoint responsible for searching all spreadsheets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spreadsheets found successfully!"),
            @ApiResponse(responseCode = "400", description = "Error searching for all spreadsheets!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<List<SpreadsheetResponseDTO>> getAllSpreadsheets() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint responsible for searching a spreadsheet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spreadsheet found successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Spreadsheet not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<SpreadsheetResponseDTO> findSpreadsheetById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint responsible for deleting a spreadsheet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spreadsheet deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Spreadsheet not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<Void> findByIdAndDelete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/all")
    @Operation(description = "Endpoint responsible for deleting all spreadsheets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Spreadsheets deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "Error deleting all spreadsheets!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(description = "Endpoint responsible for partially updating a spreadsheet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spreadsheet updated successfully!"),
            @ApiResponse(responseCode = "400", description = "Invalid input!"),
            @ApiResponse(responseCode = "401", description = "Client does not have permission for the resource!"),
            @ApiResponse(responseCode = "403", description = "Authentication failed or was not provided!"),
            @ApiResponse(responseCode = "404", description = "Spreadsheet not found!"),
            @ApiResponse(responseCode = "500", description = "Internal server error!")
    })
    public ResponseEntity<SpreadsheetResponseDTO> updateSpreadsheet(@PathVariable UUID id, @RequestBody @Valid SpreadsheetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateSpreadsheet(id, dto));
    }
}
