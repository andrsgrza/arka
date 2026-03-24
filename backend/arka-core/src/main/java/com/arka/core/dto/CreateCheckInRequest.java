package com.arka.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCheckInRequest {

    private Integer craving;
    private Integer mood;
    private Integer energy;
    private Integer sleepQuality;
    private String context;
    private Boolean wantsToConsume;
}