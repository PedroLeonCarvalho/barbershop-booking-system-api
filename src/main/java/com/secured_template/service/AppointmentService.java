package com.secured_template.service;

import com.secured_template.domain.Appointment;
import com.secured_template.domain.BarberService;
import com.secured_template.domain.User;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.repository.AppointmentRepository;
import com.secured_template.repository.BarberServiceRepository;
import com.secured_template.repository.TimeSlotRepository;
import com.secured_template.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


        var serviceId = appointmentDto.getServiceId();
        BarberService service = serviceRepository.findServiceById(serviceId);
        appointment.setService(service);

        var professionalId = appointmentDto.getProfessionalId();
        User professional = userRepository.findUserById(professionalId);
        appointment.setProfessional(professional);



        var customerlId = appointmentDto.getCustomerId();
        User customer = userRepository.findUserById(customerlId);
        appointment.setCustomer(customer);

        appointmentRepository.save(appointment);

        return convertApponintment(appointment);

    }

    public AppointmentResponseDto convertApponintment ( Appointment appointment) {

        AppointmentResponseDto responseDto = new AppointmentResponseDto();
        responseDto.setAppointmentDate(appointment.getAppointmentDate());
        responseDto.setAppointmentTime(appointment.getAppointmentTime());
        responseDto.setServicePrice(appointment.getService().getPrice());
        responseDto.setProfessionalName(appointment.getProfessional().getName());
        responseDto.setCustomerName(appointment.getCustomer().getName());
        responseDto.setServiceName(appointment.getService().getName());
        responseDto.setCustomerName(appointment.getCustomer().getName());
        responseDto.setComments(appointment.getComments());

        return responseDto;

    }


    public void deleteAppointment(Long id) {
        var appointment = appointmentRepository.getAppointmentById(id);
        appointment.setActive(false);

        var date = appointment.getAppointmentDate();
        var time = appointment.getAppointmentTime();
        var barberId = appointment.getProfessional().getId();

        var timeslot = timeSlotRepository.findByAppointmentDateAndAvailableTimeAndBarberId(date,time,barberId);
        timeslot.setBooked(false);

        timeSlotRepository.save(timeslot);
        appointmentRepository.save(appointment);

    }

    public AppointmentResponseDto getMyAppointments(Long id) {
        var appointment = appointmentRepository.findBycustomerId(id);

        return convertApponintment(appointment);

    }

    public List<AppointmentResponseDto> getMyAppointmentsBarbarber(Long id) {

        var appointment = appointmentRepository.findAppointmentsByProfessionalId(id);
        return
                appointment.stream()
                        .map(this::convertApponintment)
                        .collect(Collectors.toList());
    }



    public List<AppointmentResponseDto> getMyCurrentDayBarberAppointments(Long id) {
        var appointment = appointmentRepository.findAppointmentsByProfessionalIdAndAppointmentDate(id, LocalDate.now());
        return
                appointment.stream()
                        .map(this::convertApponintment)
                        .collect(Collectors.toList());
    }

    public List<AppointmentResponseDto> getMyEspecificDayBarberAppointments(Long id, LocalDate date) {
        var appointment = appointmentRepository.findAppointmentsByProfessionalIdAndAppointmentDate(id, date);
        return
                appointment.stream()
                        .map(this::convertApponintment)
                        .collect(Collectors.toList());
    }
}
