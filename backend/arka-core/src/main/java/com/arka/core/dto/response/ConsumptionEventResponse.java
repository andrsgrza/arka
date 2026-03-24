package com.arka.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ConsumptionEventResponse {

    private final Long id;
    private final Long userId;
    private final Integer amount;
    private final String method;
    private final String context;
    private final Integer expectedPleasure;
    private final Integer expectedRelief;
    private final LocalDateTime createdAt;
}