package com.arka.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consumption_events")
@Getter
@Setter
@NoArgsConstructor
public class ConsumptionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false)
    private Integer expectedPleasure;

    @Column(nullable = false)
    private Integer expectedRelief;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}