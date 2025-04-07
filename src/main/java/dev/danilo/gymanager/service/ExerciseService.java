package dev.danilo.gymanager.service;

import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository repository;

    private final ExerciseMapper mapper;


    public ExerciseService(ExerciseRepository repository, ExerciseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
