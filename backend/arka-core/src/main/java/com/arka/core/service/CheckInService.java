package com.arka.core.service;

import com.arka.core.dto.CreateCheckInRequest;
import com.arka.core.dto.response.CheckInResponse;
import com.arka.core.mapper.CheckInMapper;
import com.arka.core.model.CheckIn;
import com.arka.core.model.User;
import com.arka.core.repository.CheckInRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final DevUserService devUserService;

    public CheckInService(CheckInRepository checkInRepository, DevUserService devUserService) {
        this.checkInRepository = checkInRepository;
        this.devUserService = devUserService;
    }

    public CheckInResponse createCheckIn(CreateCheckInRequest request) {
        User user = devUserService.getOrCreateDevUser();

        CheckIn checkIn = new CheckIn();
        checkIn.setUser(user);
        checkIn.setCraving(request.getCraving());
        checkIn.setMood(request.getMood());
        checkIn.setEnergy(request.getEnergy());
        checkIn.setSleepQuality(request.getSleepQuality());
        checkIn.setContext(request.getContext());
        checkIn.setWantsToConsume(request.getWantsToConsume());
        checkIn.setCreatedAt(LocalDateTime.now());

        CheckIn savedCheckIn = checkInRepository.save(checkIn);

        return CheckInMapper.toResponse(savedCheckIn);
    }

    public Optional<CheckInResponse> getLatestCheckIn() {
        return checkInRepository.findTopByOrderByCreatedAtDesc()
                .map(CheckInMapper::toResponse);
    }
}