package com.secured_template.service;

import com.secured_template.domain.TimeSlot;
import com.secured_template.infra.exception.AppointmentTimeUnavailableException;
import com.secured_template.repository.TimeSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getAvailableTimeSlots (LocalDate date, Long barberId) throws AppointmentTimeUnavailableException {

            // Verifica se o dia é válido
            if (!isValidDay(date)) {
                throw new AppointmentTimeUnavailableException("Barbearia fechada nesse dia ou você está tentando marcar com antecedência superior a 30 dias.");
            }

            // Gera os horários dinamicamente
            List<LocalTime> availableTimes = getAvailableTimes();
            List<TimeSlot> availableSlots = new ArrayList<>();
            // Busca os horários já reservados para o barbeiro específico

        List<TimeSlot> bookedSlots = timeSlotRepository.findByAppointmentDateAndBarberIdAndBookedTrue(date, barberId);
            // Verifica quais horários ainda não estão reservados
            for (LocalTime time : availableTimes) {
                boolean isAlreadyBooked = bookedSlots.stream()
                        .anyMatch(slot -> slot.getAvailableTime().equals(time));

                if (!isAlreadyBooked) {
                    TimeSlot slot = new TimeSlot();
                    slot.setAppointmentDate(date);
                    slot.setAvailableTime(time);
                    slot.setBooked(false);
                    slot.setBarberId(barberId);// O horário não está reservado
                    availableSlots.add(slot);
                }
            }
             return availableSlots; // Retorna a lista de horários disponíveis
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



    @Transactional
    public void   bookTimeSlot(String data , String time, Long barberId) throws AppointmentTimeUnavailableException {
        // Converte a string "data" para LocalDate
        LocalDate appointmentDate = LocalDate.parse(data);

        // Converte a string "time" para LocalTime
        LocalTime appointmentTime = LocalTime.parse(time);

        TimeSlot timeSlot = timeSlotRepository.findByAppointmentDateAndAvailableTimeAndBarberIdAndBookedTrue(appointmentDate, appointmentTime, barberId);
        if( timeSlot == null) {
            TimeSlot newTimeSlot = new TimeSlot();
            newTimeSlot.setAppointmentDate(appointmentDate);
            newTimeSlot.setAvailableTime(appointmentTime);
            newTimeSlot.setBarberId(barberId);
            newTimeSlot.setBooked(true);  // Marca o horário como reservado
            timeSlotRepository.save(newTimeSlot);
        }

      else {
            throw new AppointmentTimeUnavailableException("Horário indisponível, por favor, escolha outro horário ou data");
        }




    }
    @Transactional
    public void   turnAllTimeSlotBookedTrue(String data ,  Long barberId) throws IllegalStateException {

        // Converte a string "data" para LocalDate
        LocalDate appointmentDate = LocalDate.parse(data);
        List<TimeSlot> alreadyBookedTimeSlotsByCustomer = timeSlotRepository.findByAppointmentDateAndBarberIdAndBookedTrue(appointmentDate, barberId);

        List<LocalTime> timesAlreadyBookedList = alreadyBookedTimeSlotsByCustomer.stream()
                .map(TimeSlot::getAvailableTime)
                .collect(Collectors.toList());

        // Gera uma nova lista mutável com os horários disponíveis
        var tsList = new ArrayList<>(getAvailableTimes());

        tsList.removeIf(timesAlreadyBookedList::contains);

            for(int i = 0; i<tsList.size(); i++) {

                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setAvailableTime(tsList.get(i));
                timeSlot.setAppointmentDate(appointmentDate);
                timeSlot.setBarberId(barberId);
                timeSlot.setBooked(true);
                timeSlotRepository.save(timeSlot);
            }
        }



    // Gera horários disponíveis para o dia (futuramente pode ser transformada em uma entidade pra ser mais configurável)
    private List<LocalTime> getAvailableTimes() {
        return List.of(

                LocalTime.of(9, 0),
                LocalTime.of(9, 30),
                LocalTime.of(10, 0),
                LocalTime.of(10, 30),
                LocalTime.of(11, 0),
                LocalTime.of(11, 30),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(13, 30),
                LocalTime.of(14, 0),
                LocalTime.of(14, 30),
                LocalTime.of(15, 0),
                LocalTime.of(15, 30),
                LocalTime.of(16, 0),
                LocalTime.of(16, 30)
        );
    }

    public void setFreeTimeSlot(String date, String time, Long barberId) {

      TimeSlot timeSlot = timeSlotRepository.findByAppointmentDateAndAvailableTimeAndBarberId(LocalDate.parse(date), LocalTime.parse(time), barberId);

        timeSlot.setAvailableTime(LocalTime.parse(time));
        timeSlot.setAppointmentDate(LocalDate.parse(date));
        timeSlot.setBooked(false);
        timeSlot.setBarberId(barberId);

        timeSlotRepository.save(timeSlot);

    }
}
