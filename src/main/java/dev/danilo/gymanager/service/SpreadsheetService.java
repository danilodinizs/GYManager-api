package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.mapper.SpreadsheetMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
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
        log.info("Saving a Spreadsheet");
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public SpreadsheetResponseDTO findById(UUID id) {
        Optional<Spreadsheet> spreadsheet = repository.findById(id);

        log.info("Spreadsheet found by id: " + id + " or returning null");
        return spreadsheet.map(mapper::toDto).orElseThrow(null); //exception here
    }

    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            // exception here: throw new Exception("Not found ")
        }
        log.info("Deleting spreadsheet by id: " + id);
        repository.deleteById(id);
    }

    public SpreadsheetResponseDTO updateSpreadsheet(UUID id, SpreadsheetRequestDTO dto) {

        log.info("Starting the process of updating a Spreadsheet");

        Optional<Spreadsheet> spreadsheet = repository.findById(id); // exception here .orElseThrow(() -> new )

        log.info("Spreadsheet found by id: " + id);

        if(spreadsheet.isPresent()) {
            Spreadsheet newSpreadsheet = spreadsheet.get();

            newSpreadsheet.setName(dto.name());
            newSpreadsheet.setDescription(dto.description());
            newSpreadsheet.setDate(dto.date());

            log.info("Saving new spreadsheet: " + newSpreadsheet.toString());

            return mapper.toDto(repository.save(newSpreadsheet));
        }
        return null;
    }

    public void deleteAll() {

        log.info("Deleting all spreadsheets");

        repository.deleteAll();
    }
}
