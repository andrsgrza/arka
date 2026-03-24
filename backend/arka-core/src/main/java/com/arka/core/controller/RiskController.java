package com.arka.core.controller;

import com.arka.core.dto.response.RiskResponse;
import com.arka.core.service.RiskEngineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskController {

    private final RiskEngineService riskEngineService;

    public RiskController(RiskEngineService riskEngineService) {
        this.riskEngineService = riskEngineService;
    }

    @GetMapping("/risk")
    public ResponseEntity<RiskResponse> getRisk() {
        return riskEngineService.getCurrentRisk()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}