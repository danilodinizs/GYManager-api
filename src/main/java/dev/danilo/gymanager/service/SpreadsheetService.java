package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.mapper.SpreadsheetMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class SpreadsheetService {

    private final SpreadsheetRepository repository;

    private final SpreadsheetMapper mapper;

    public SpreadsheetService(SpreadsheetRepository repository, SpreadsheetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<SpreadsheetResponseDTO> findAll() {
        log.info("Looking for all spreadsheets");
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public SpreadsheetResponseDTO save(SpreadsheetRequestDTO dto) {
        log.info("Saving a Spreadsheet: {}", dto.toString());
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public SpreadsheetResponseDTO findById(UUID id) {
        log.info("Searching spreadsheet by id: {}", id);
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new EntityNotFoundException("Spreadsheet not found by id: " + id));
    }

    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Spreadsheet not found by id: " + id);
        }
        log.info("Deleting spreadsheet by id: {}", id);
        repository.deleteById(id);
    }

    public SpreadsheetResponseDTO updateSpreadsheet(UUID id, SpreadsheetRequestDTO dto) {

        log.info("Starting the process of updating a Spreadsheet");

        Spreadsheet spreadsheet = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Spreadsheet not found by id: " + id));

        log.info("Spreadsheet found by id: {}", id);

        spreadsheet.setName(dto.name());
        spreadsheet.setDescription(dto.description());
        spreadsheet.setDate(dto.date());

        log.info("Saving new spreadsheet: {}", spreadsheet.toString());

        return mapper.toDto(repository.save(spreadsheet));
    }

    public void deleteAll() {

        log.info("Deleting all spreadsheets");

        repository.deleteAll();
    }
}
