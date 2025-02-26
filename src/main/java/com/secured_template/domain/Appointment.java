package com.secured_template.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "professional_id", referencedColumnName = "id", nullable = false)
    private User professional;

    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    private BarberService service;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedAt;

    @Column(name = "is_active")
    private boolean isActive = true;
    @Column(name = "comments")
    private String comments;

}