package com.secured_template.service;

import com.secured_template.domain.Appointment;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.repository.AppointmentRepository;
import com.secured_template.repository.BarberServiceRepository;
import com.secured_template.repository.TimeSlotRepository;
import com.secured_template.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final BarberServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final TimeSlotService timeSlotService;
    private final TimeSlotRepository timeSlotRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, BarberServiceRepository serviceRepository, UserRepository userRepository, TimeSlotService timeSlotService,
                              TimeSlotRepository timeSlotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.timeSlotService = timeSlotService;
        this.timeSlotRepository = timeSlotRepository;
    }


    public  AppointmentResponseDto createAppointment(AppointmentDto appointmentDto) {

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setServiceId(appointmentDto.getServiceId());
        appointment.setCustomerId(appointmentDto.getCustomerId());
        appointment.setProfessionalId(appointmentDto.getProfessionalId());
        appointmentRepository.save(appointment);
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


    public void deleteAppointment(Long id) {
        var appointment = appointmentRepository.getAppointmentById(id);
        appointment.setActive(false);
        var date = appointment.getAppointmentDate();
        var time = appointment.getAppointmentTime();
        var barberId = appointment.getProfessionalId();

        var timeslot = timeSlotRepository.findByAppointmentDateAndAvailableTimeAndBarberId(date,time,barberId);
        timeslot.setBooked(false);

        appointmentRepository.save(appointment);

    }

    public AppointmentResponseDto getMyAppointments(Long id) {
        var appointment = appointmentRepository.findBycustomerId(id);

        return convertApponintment(appointment);

    }
}
