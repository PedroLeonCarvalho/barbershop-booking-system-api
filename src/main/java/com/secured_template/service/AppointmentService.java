package com.secured_template.service;

import com.secured_template.domain.Appointment;
import com.secured_template.domain.BarberService;
import com.secured_template.domain.User;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.dto.RevenueDto;
import com.secured_template.infra.exception.AppointmentTimeUnavailableException;
import com.secured_template.repository.AppointmentRepository;
import com.secured_template.repository.BarberServiceRepository;
import com.secured_template.repository.TimeSlotRepository;
import com.secured_template.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    public  AppointmentResponseDto createAppointment(AppointmentDto appointmentDto) throws AppointmentTimeUnavailableException {
        // Verifica se o dia é válido
        if (!isValidDay(appointmentDto.getAppointmentDate())) {
            throw new AppointmentTimeUnavailableException ("Barbearia fechada nesse dia.");
        }
        Appointment appointment = new Appointment();

        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setComments(appointmentDto.getComments());

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

        var timeslot = timeSlotRepository.findByAppointmentDateAndAvailableTimeAndBarberIdAndBookedTrue(date,time,barberId);
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

    // Verifica se a barbearia está aberta no dia especificado
    private boolean isValidDay(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            return false;
        }
        // Verifica se a data está muito no futuro (exemplo: mais de 3 meses à frente)
        if (date.isAfter(LocalDate.now().plusMonths(1))) {
            return false; // Não permite agendamento com mais de 1 mês de antecendencia
        }
        // Exemplo: a barbearia abre de segunda a sábado
        if (date.getDayOfWeek().equals(java.time.DayOfWeek.SUNDAY)) {
            return false;
        }

        return true;
    }

    public RevenueDto getRevenue(Long id) {
        var appointList =  appointmentRepository.findAppointmentsByProfessionalId(id);
        // Somar os preços dos serviços de cada appointment
        BigDecimal totalRevenue = appointList.stream()
                .map(appointment -> appointment.getService().getPrice()) // Mapeia cada appointment para o preço do serviço
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Reduz a lista somando os preços de maneira segura

        RevenueDto dto = new RevenueDto();

        var appointCount = appointList.size();
        dto.setTotalOfTheDay(totalRevenue);
        dto.setNumberOfCostomers(appointCount);
        // Para calcular a média de preço por cliente usando BigDecimal
        if (appointCount > 0) {
            BigDecimal appointCountBigDecimal = BigDecimal.valueOf(appointCount);
            BigDecimal averagePricePerCustomer = totalRevenue.divide(appointCountBigDecimal, RoundingMode.HALF_UP);
            dto.setAvaragePricePerCostumer(averagePricePerCustomer);
        } else {
            dto.setAvaragePricePerCostumer(BigDecimal.ZERO); // No caso de não haver clientes
        }


        return dto;


    }
}
