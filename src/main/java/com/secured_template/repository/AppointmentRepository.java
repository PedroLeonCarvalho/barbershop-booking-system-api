package com.secured_template.repository;

import com.secured_template.domain.Appointment;
import com.secured_template.service.AppointmentService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository <Appointment, Long > {
    Appointment getAppointmentById(Long id);

    Appointment findBycustomerId(Long id);
}
