package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.mapper.SpreadsheetMapper;
import dev.danilo.gymanager.service.SpreadsheetService;
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
    public ResponseEntity<SpreadsheetResponseDTO> saveSpreadsheet(@RequestBody @Valid SpreadsheetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<SpreadsheetResponseDTO>> getAllSpreadsheets() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpreadsheetResponseDTO> findSpreadsheetById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> findByIdAndDelete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SpreadsheetResponseDTO> updateSpreadsheet(@PathVariable UUID id, @RequestBody @Valid SpreadsheetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateSpreadsheet(id, dto));
    }
}
