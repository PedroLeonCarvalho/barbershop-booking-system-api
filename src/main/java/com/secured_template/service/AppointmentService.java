package com.secured_template.service;

import com.secured_template.domain.Appointment;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.repository.BarberServiceRepository;
import com.secured_template.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    private final BarberServiceRepository serviceRepository;
    private final UserRepository userRepository;

    public AppointmentService(BarberServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }


    public  AppointmentResponseDto createAppointment(AppointmentDto appointmentDto) {

        Appointment appointment = new Appointment();
    appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
    appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
    appointment.setServiceId(appointmentDto.getServiceId());
    appointment.setCustomerId(appointmentDto.getCustomerId());
    appointment.setProfessionalId(appointmentDto.getProfessionalId());

    return convertApponintment(appointment);

    }

    public AppointmentResponseDto convertApponintment ( Appointment appointment) {

        var service = serviceRepository.findServiceById(appointment.getServiceId());
        var professional = userRepository.findUserById(appointment.getProfessionalId());

        AppointmentResponseDto responseDto = new AppointmentResponseDto();
        responseDto.setAppointmentDate(appointment.getAppointmentDate());
        responseDto.setAppointmentTime(appointment.getAppointmentTime());
        responseDto.setServicePrice(service.getPrice());
        responseDto.setProfessionalName(professional.getName());
        responseDto.setServiceName(service.getName());

        return responseDto;

    }



}
