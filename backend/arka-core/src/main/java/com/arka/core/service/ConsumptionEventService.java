package com.arka.core.service;

import com.arka.core.dto.request.CreateConsumptionEventRequest;
import com.arka.core.dto.response.ConsumptionEventResponse;
import com.arka.core.mapper.ConsumptionEventMapper;
import com.arka.core.model.ConsumptionEvent;
import com.arka.core.model.User;
import com.arka.core.repository.ConsumptionEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConsumptionEventService {

    private final ConsumptionEventRepository consumptionEventRepository;
    private final DevUserService devUserService;

    public ConsumptionEventService(
            ConsumptionEventRepository consumptionEventRepository,
            DevUserService devUserService
    ) {
        this.consumptionEventRepository = consumptionEventRepository;
        this.devUserService = devUserService;
    }

    public ConsumptionEventResponse createConsumptionEvent(CreateConsumptionEventRequest request) {
        User user = devUserService.getOrCreateDevUser();

        ConsumptionEvent event = new ConsumptionEvent();
        event.setUser(user);
        event.setAmount(request.getAmount());
        event.setMethod(request.getMethod());
        event.setContext(request.getContext());
        event.setExpectedPleasure(request.getExpectedPleasure());
        event.setExpectedRelief(request.getExpectedRelief());
        event.setCreatedAt(LocalDateTime.now());

        ConsumptionEvent savedEvent = consumptionEventRepository.save(event);

        return ConsumptionEventMapper.toResponse(savedEvent);
    }

    public Optional<ConsumptionEventResponse> getLatestConsumptionEvent() {
        User user = devUserService.getOrCreateDevUser();

        return consumptionEventRepository.findTopByUserOrderByCreatedAtDesc(user)
                .map(ConsumptionEventMapper::toResponse);
    }
}