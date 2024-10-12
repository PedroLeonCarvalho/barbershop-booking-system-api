package com.secured_template.repository;

import com.secured_template.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository <TimeSlot, Long> {

    List<TimeSlot> findByAppointmentDateAndIsBookedFalse(LocalDate appointmentDate);

    List<TimeSlot> findByAppointmentDateAndIsBookedTrue(LocalDate date);

   TimeSlot findByAppointmentDateAndAvailableTime(LocalDate data, LocalTime time);

    List<TimeSlot> findByAppointmentDateAndBarberIdAndIsBookedTrue(LocalDate date, Long barberId);

    TimeSlot findByAppointmentDateAndAvailableTimeAndBarberId(LocalDate appointmentDate, LocalTime appointmentTime, Long barberId);
}
