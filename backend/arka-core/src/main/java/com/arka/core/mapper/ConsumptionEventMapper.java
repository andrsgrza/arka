package com.arka.core.mapper;

import com.arka.core.dto.response.ConsumptionEventResponse;
import com.arka.core.model.ConsumptionEvent;

public class ConsumptionEventMapper {

    private ConsumptionEventMapper() {
    }

    public static ConsumptionEventResponse toResponse(ConsumptionEvent event) {
        return new ConsumptionEventResponse(
                event.getId(),
                event.getUser().getId(),
                event.getAmount(),
                event.getMethod(),
                event.getContext(),
                event.getExpectedPleasure(),
                event.getExpectedRelief(),
                event.getCreatedAt()
        );
    }
}