package dev.danilo.gymanager.controller;

import dev.danilo.gymanager.mapper.WorkoutMapper;
import dev.danilo.gymanager.service.WorkoutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService service;

    private final WorkoutMapper mapper;


    public WorkoutController(WorkoutService service, WorkoutMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
}
