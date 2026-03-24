package com.arka.core.service;

import com.arka.core.dto.response.InterventionResponse;
import com.arka.core.dto.response.RiskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {

    private final RiskEngineService riskEngineService;

    public InterventionService(RiskEngineService riskEngineService) {
        this.riskEngineService = riskEngineService;
    }

    public List<InterventionResponse> getCurrentInterventions() {
        RiskResponse risk = riskEngineService.getCurrentRisk()
                .orElse(new RiskResponse(0, "LOW", List.of()));

        return mapInterventions(risk.getLevel());
    }

    private List<InterventionResponse> mapInterventions(String level) {
        return switch (level) {
            case "HIGH" -> List.of(
                    new InterventionResponse(
                            "breathing",
                            "Breathing Reset",
                            "Pause and do a short breathing exercise to reduce immediate intensity.",
                            3
                    ),
                    new InterventionResponse(
                            "exercise",
                            "Quick Exercise",
                            "Do a brief burst of physical movement to shift your state.",
                            10
                    ),
                    new InterventionResponse(
                            "delay_10_min",
                            "Delay 10 Minutes",
                            "Commit to waiting 10 minutes before making any decision to consume.",
                            10
                    ),
                    new InterventionResponse(
                            "leave_context",
                            "Change Environment",
                            "Step away from the current environment and interrupt the pattern.",
                            5
                    )
            );
            case "MEDIUM" -> List.of(
                    new InterventionResponse(
                            "breathing",
                            "Breathing Reset",
                            "Slow down and regulate your nervous system with intentional breathing.",
                            3
                    ),
                    new InterventionResponse(
                            "short_walk",
                            "Short Walk",
                            "Take a short walk to reduce momentum toward consumption.",
                            10
                    ),
                    new InterventionResponse(
                            "journaling",
                            "Quick Reflection",
                            "Write down what you want from consuming and what it may cost you later.",
                            5
                    )
            );
            default -> List.of(
                    new InterventionResponse(
                            "reflection",
                            "Brief Reflection",
                            "Take a moment to notice your current state and intention.",
                            2
                    ),
                    new InterventionResponse(
                            "light_walk",
                            "Light Walk",
                            "Move a little to maintain momentum and clarity.",
                            5
                    )
            );
        };
    }
}