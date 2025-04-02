package dev.danilo.gymanager.repository;

import dev.danilo.gymanager.entity.Spreadsheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpreadsheetRepository extends JpaRepository<Spreadsheet, UUID> {
}
