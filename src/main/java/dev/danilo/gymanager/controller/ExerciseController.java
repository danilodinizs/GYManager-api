package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.mapper.ExerciseMapper;
import dev.danilo.gymanager.service.ExerciseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService service;
    private final ExerciseMapper mapper;


    public ExerciseController(ExerciseService service, ExerciseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
}
