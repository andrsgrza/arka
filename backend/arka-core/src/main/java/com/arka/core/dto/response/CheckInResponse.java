package com.arka.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CheckInResponse {

    private final Long id;
    private final Long userId;
    private final Integer craving;
    private final Integer mood;
    private final Integer energy;
    private final Integer sleepQuality;
    private final String context;
    private final Boolean wantsToConsume;
    private final LocalDateTime createdAt;
}