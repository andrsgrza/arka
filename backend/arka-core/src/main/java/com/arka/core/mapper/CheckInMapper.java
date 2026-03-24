package com.arka.core.mapper;

import com.arka.core.dto.response.CheckInResponse;
import com.arka.core.model.CheckIn;

public class CheckInMapper {

    private CheckInMapper() {
    }

    public static CheckInResponse toResponse(CheckIn checkIn) {
        return new CheckInResponse(
                checkIn.getId(),
                checkIn.getCraving(),
                checkIn.getMood(),
                checkIn.getEnergy(),
                checkIn.getSleepQuality(),
                checkIn.getContext(),
                checkIn.getWantsToConsume(),
                checkIn.getCreatedAt()
        );
    }
}