package com.arka.core.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateConsumptionEventRequest {

    private Integer amount;
    private String method;
    private String context;
    private Integer expectedPleasure;
    private Integer expectedRelief;
}