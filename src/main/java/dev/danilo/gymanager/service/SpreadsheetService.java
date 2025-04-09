package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.mapper.SpreadsheetMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpreadsheetService {

    private final SpreadsheetRepository repository;

    private final SpreadsheetMapper mapper;

    public SpreadsheetService(SpreadsheetRepository repository, SpreadsheetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<SpreadsheetResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public SpreadsheetResponseDTO save(SpreadsheetRequestDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public SpreadsheetResponseDTO findById(UUID id) {
        Optional<Spreadsheet> spreadsheet = repository.findById(id);
        return spreadsheet.map(mapper::toDto).orElseThrow(null); //exception here
    }

    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            // exception here: throw new Exception("Not found ")
        }
        repository.deleteById(id);
    }

    public SpreadsheetResponseDTO updateSpreadsheet(UUID id, SpreadsheetRequestDTO dto) {
        Optional<Spreadsheet> spreadsheet = repository.findById(id); // exception here .orElseThrow(() -> new )

        if(spreadsheet.isPresent()) {
            Spreadsheet newSpreadsheet = spreadsheet.get();

            newSpreadsheet.setName(dto.name());
            newSpreadsheet.setDescription(dto.description());
            newSpreadsheet.setDate(dto.date());

            return mapper.toDto(repository.save(newSpreadsheet));
        }
        return null;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
