package com.arka.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RiskResponse {

    private final Integer score;
    private final String level;
    private final List<String> factors;
}