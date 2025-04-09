package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.mapper.SpreadsheetMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import org.hibernate.annotations.NotFound;
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
        return mapper.toDtoList(repository.findAll());
    }

    public void save(SpreadsheetRequestDTO dto) {
        repository.save(mapper.toEntity(dto));
    }

    public SpreadsheetResponseDTO findById(UUID id) {
        Optional<Spreadsheet> spreadsheet = repository.findById(id);
        return spreadsheet.map(mapper::toDto).orElse(null);
    }

    public void delete(UUID id) {
        Optional<Spreadsheet> spreadsheet = repository.findById(id);
        spreadsheet.ifPresent(repository::delete);
    }

    public SpreadsheetResponseDTO updateSpreadsheet(UUID id, SpreadsheetRequestDTO dto) {
        Optional<Spreadsheet> spreadsheet = repository.findById(id);

        if(spreadsheet.isPresent()) {
            Spreadsheet updatedSpreadsheet = spreadsheet.get();
            updatedSpreadsheet.setName(dto.name());
            updatedSpreadsheet.setDescription(dto.description());
            updatedSpreadsheet.setDate(dto.date());
            return mapper.toDto(repository.save(updatedSpreadsheet));
        } else {
            return null;
        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
