-- V2__Create_workout_table.sql
CREATE TABLE workout (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    day_of_week VARCHAR(20),
    spreadsheet_id UUID NOT NULL,
    CONSTRAINT fk_workout_spreadsheet FOREIGN KEY (spreadsheet_id) REFERENCES spreadsheet(id)
);