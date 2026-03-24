package com.arka.core.repository;

import com.arka.core.model.ConsumptionEvent;
import com.arka.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumptionEventRepository extends JpaRepository<ConsumptionEvent, Long> {

    Optional<ConsumptionEvent> findTopByUserOrderByCreatedAtDesc(User user);
}