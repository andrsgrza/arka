package com.arka.core.controller;

import com.arka.core.dto.request.CreateConsumptionEventRequest;
import com.arka.core.dto.response.ConsumptionEventResponse;
import com.arka.core.service.ConsumptionEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumption-events")
public class ConsumptionEventController {

    private final ConsumptionEventService consumptionEventService;

    public ConsumptionEventController(ConsumptionEventService consumptionEventService) {
        this.consumptionEventService = consumptionEventService;
    }

    @PostMapping
    public ResponseEntity<ConsumptionEventResponse> createConsumptionEvent(
            @RequestBody CreateConsumptionEventRequest request
    ) {
        ConsumptionEventResponse created = consumptionEventService.createConsumptionEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/latest")
    public ResponseEntity<ConsumptionEventResponse> getLatestConsumptionEvent() {
        return consumptionEventService.getLatestConsumptionEvent()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}