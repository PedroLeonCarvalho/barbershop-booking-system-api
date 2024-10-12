package com.secured_template.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlotDto {

    private Long id;
    private LocalDate appointmentDate;
    private LocalTime availableTime;
    private boolean isBooked;

}
