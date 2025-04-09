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
@RequestMapping("/spreadsheet")
public class SpreadsheetController {

    private final SpreadsheetService service;

    private final SpreadsheetMapper mapper;

    public SpreadsheetController(SpreadsheetService service, SpreadsheetMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<SpreadsheetResponseDTO>> getAllSpreadsheets() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> saveSpreadsheet(@RequestBody @Valid SpreadsheetRequestDTO dto) {
        System.out.println("Dados recebidos: " + dto.name() + ", " + dto.description() + ", " + dto.date());
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpreadsheetResponseDTO> findSpreadsheetById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> findByIdAndDelete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SpreadsheetResponseDTO> updateSpreadsheet(@PathVariable UUID id, @RequestBody @Valid SpreadsheetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateSpreadsheet(id, dto));
    }
}
