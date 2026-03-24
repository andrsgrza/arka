package com.arka.core.service;

import com.arka.core.dto.CreateCheckInRequest;
import com.arka.core.model.CheckIn;
import com.arka.core.repository.CheckInRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;

    public CheckInService(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    public CheckIn createCheckIn(CreateCheckInRequest request) {
        CheckIn checkIn = new CheckIn();
        checkIn.setCraving(request.getCraving());
        checkIn.setMood(request.getMood());
        checkIn.setEnergy(request.getEnergy());
        checkIn.setSleepQuality(request.getSleepQuality());
        checkIn.setContext(request.getContext());
        checkIn.setWantsToConsume(request.getWantsToConsume());
        checkIn.setCreatedAt(LocalDateTime.now());

        return checkInRepository.save(checkIn);
    }

    public Optional<CheckIn> getLatestCheckIn() {
        return checkInRepository.findTopByOrderByCreatedAtDesc();
    }
}