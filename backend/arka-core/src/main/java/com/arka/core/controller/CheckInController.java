package com.arka.core.controller;

import com.arka.core.dto.CreateCheckInRequest;
import com.arka.core.dto.response.CheckInResponse;
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
    public ResponseEntity<CheckInResponse> createCheckIn(@RequestBody CreateCheckInRequest request) {
        CheckInResponse createdCheckIn = checkInService.createCheckIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCheckIn);
    }

    @GetMapping("/latest")
    public ResponseEntity<CheckInResponse> getLatestCheckIn() {
        return checkInService.getLatestCheckIn()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}