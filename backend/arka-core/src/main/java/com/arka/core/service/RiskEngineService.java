package com.arka.core.service;

import com.arka.core.dto.response.RiskResponse;
import com.arka.core.model.CheckIn;
import com.arka.core.model.User;
import com.arka.core.repository.CheckInRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RiskEngineService {

    private final CheckInRepository checkInRepository;
    private final DevUserService devUserService;

    public RiskEngineService(CheckInRepository checkInRepository, DevUserService devUserService) {
        this.checkInRepository = checkInRepository;
        this.devUserService = devUserService;
    }

    public Optional<RiskResponse> getCurrentRisk() {
        User user = devUserService.getOrCreateDevUser();

        return checkInRepository.findTopByUserOrderByCreatedAtDesc(user)
                .map(this::calculateRisk);
    }

    private RiskResponse calculateRisk(CheckIn checkIn) {
        int score = 0;
        List<String> factors = new ArrayList<>();

        Integer craving = checkIn.getCraving();
        Integer mood = checkIn.getMood();
        Integer energy = checkIn.getEnergy();
        Integer sleepQuality = checkIn.getSleepQuality();
        Boolean wantsToConsume = checkIn.getWantsToConsume();

        if (craving != null) {
            if (craving >= 8) {
                score += 40;
                factors.add("high_craving");
            } else if (craving >= 6) {
                score += 25;
                factors.add("elevated_craving");
            }
        }

        if (mood != null && mood <= 3) {
            score += 10;
            factors.add("low_mood");
        }

        if (energy != null && energy <= 3) {
            score += 10;
            factors.add("low_energy");
        }

        if (sleepQuality != null && sleepQuality <= 3) {
            score += 10;
            factors.add("poor_sleep");
        }

        if (Boolean.TRUE.equals(wantsToConsume)) {
            score += 20;
            factors.add("wants_to_consume");
        }

        if (score > 100) {
            score = 100;
        }

        String level = mapLevel(score);

        return new RiskResponse(score, level, factors);
    }

    private String mapLevel(int score) {
        if (score >= 70) {
            return "HIGH";
        }

        if (score >= 35) {
            return "MEDIUM";
        }

        return "LOW";
    }
}