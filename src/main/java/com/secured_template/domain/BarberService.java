package com.secured_template.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BarberService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    public BarberService(String description, String name, BigDecimal price, Boolean isActive, Integer durationMinutes) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.isActive = isActive;
        this.durationMinutes = durationMinutes;
    }
}