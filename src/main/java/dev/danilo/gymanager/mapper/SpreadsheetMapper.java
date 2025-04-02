package dev.danilo.gymanager.mapper;

import dev.danilo.gymanager.dto.SpreadsheetRequestDTO;
import dev.danilo.gymanager.dto.SpreadsheetResponseDTO;
import dev.danilo.gymanager.entity.Spreadsheet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpreadsheetMapper {

    SpreadsheetResponseDTO toDto(Spreadsheet spreadsheet);

    Spreadsheet toEntity(SpreadsheetRequestDTO dto);

    List<SpreadsheetResponseDTO> toDtoList(List<Spreadsheet> spreadsheetList);
    List<Spreadsheet> toEntityList(List<SpreadsheetRequestDTO> spreadsheetList);
}
