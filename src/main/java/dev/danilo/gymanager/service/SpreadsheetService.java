package dev.danilo.gymanager.service;

import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import dev.danilo.gymanager.mapper.SpreadsheetMapper;
import dev.danilo.gymanager.repository.SpreadsheetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
