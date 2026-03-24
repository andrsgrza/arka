package com.arka.core.repository;

import com.arka.core.model.CheckIn;
import com.arka.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

    Optional<CheckIn> findTopByOrderByCreatedAtDesc();

    Optional<CheckIn> findTopByUserOrderByCreatedAtDesc(User user);
}