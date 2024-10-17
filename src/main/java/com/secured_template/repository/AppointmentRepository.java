package com.secured_template.repository;

import com.secured_template.domain.Appointment;
import com.secured_template.service.AppointmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository <Appointment, Long > {
    Appointment getAppointmentById(Long id);

    Appointment findBycustomerId(Long id);

    @Query("SELECT a FROM Appointment a WHERE a.professional.id = :professionalId")
    List<Appointment> findAppointmentsByProfessionalId(@Param("professionalId") Long professionalId);

    List<Appointment> findAppointmentsByProfessionalIdAndAppointmentDate(Long id, LocalDate date);
}
