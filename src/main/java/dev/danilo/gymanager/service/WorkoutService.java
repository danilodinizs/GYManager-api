package dev.danilo.gymanager.service;

import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {

    private final WorkoutRepository repository;
    private final WorkoutMapper mapper;

    public WorkoutService(WorkoutRepository repository, WorkoutMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
