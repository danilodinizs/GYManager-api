package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.service.SpreadsheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spreadsheet")
public class SpreadsheetController {

    private final SpreadsheetService service;

    public SpreadsheetController(SpreadsheetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SpreadsheetResponseDTO>> getAllSpreadsheets() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
}
