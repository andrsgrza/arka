package com.arka.core.controller;

import com.arka.core.dto.response.InterventionResponse;
import com.arka.core.service.InterventionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterventionController {

    private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @GetMapping("/interventions")
    public List<InterventionResponse> getInterventions() {
        return interventionService.getCurrentInterventions();
    }
}