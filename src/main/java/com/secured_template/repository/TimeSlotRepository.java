package com.secured_template.repository;

import com.secured_template.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository <TimeSlot, Long> {

    List<TimeSlot> findByAppointmentDateAndBookedFalse(LocalDate appointmentDate);

    List<TimeSlot> findByAppointmentDateAndBookedTrue(LocalDate date);

   TimeSlot findByAppointmentDateAndAvailableTime(LocalDate data, LocalTime time);

    List<TimeSlot> findByAppointmentDateAndBarberIdAndBookedTrue(LocalDate date, Long barberId);

    TimeSlot findByAppointmentDateAndAvailableTimeAndBarberId(LocalDate appointmentDate, LocalTime appointmentTime, Long barberId);

    List<TimeSlot> findByAppointmentDateAndBookedFalseAndBarberId(LocalDate appointmentDate, Long barberId);

    TimeSlot findByAppointmentDateAndAvailableTimeAndBarberIdAndBookedTrue(LocalDate appointmentDate, LocalTime appointmentTime, Long barberId);
}
