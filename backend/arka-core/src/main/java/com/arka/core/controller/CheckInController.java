package com.arka.core.controller;

import com.arka.core.dto.CreateCheckInRequest;
import com.arka.core.model.CheckIn;
import com.arka.core.service.CheckInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check-ins")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping
    public ResponseEntity<CheckIn> createCheckIn(@RequestBody CreateCheckInRequest request) {
        CheckIn createdCheckIn = checkInService.createCheckIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCheckIn);
    }

    @GetMapping("/latest")
    public ResponseEntity<CheckIn> getLatestCheckIn() {
        return checkInService.getLatestCheckIn()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}