package com.arka.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterventionResponse {

    private final String code;
    private final String title;
    private final String description;
    private final Integer durationMinutes;
}