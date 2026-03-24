package com.arka.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins")
@Getter
@Setter
@NoArgsConstructor
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer craving;

    @Column(nullable = false)
    private Integer mood;

    @Column(nullable = false)
    private Integer energy;

    @Column(nullable = false)
    private Integer sleepQuality;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private Boolean wantsToConsume;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}