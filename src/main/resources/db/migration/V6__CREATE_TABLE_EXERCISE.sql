CREATE TABLE exercise (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    sets VARCHAR(255),
    reps VARCHAR(255),
    rest_time VARCHAR(255),
    technique VARCHAR(255),
    exercise_order INTEGER,
    workout_id UUID,
    CONSTRAINT fk_workout
        FOREIGN KEY (workout_id)
        REFERENCES workout(id)
);