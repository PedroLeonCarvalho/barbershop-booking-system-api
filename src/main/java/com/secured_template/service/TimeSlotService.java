package com.secured_template.service;

import com.secured_template.domain.TimeSlot;
import com.secured_template.repository.TimeSlotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getAvailableTimeSlots (LocalDate date) {
        // Verifica se o dia é válido
        if (!isValidDay(date)) {
            throw new RuntimeException("Barbearia fechada nesse dia.");
        }

        // Gera os horários dinamicamente
        List<LocalTime> availableTimes = getAvailableTimes();
        List<TimeSlot> availableSlots = new ArrayList<>();
        List<TimeSlot> bookedSlots = timeSlotRepository.findByAppointmentDateAndIsBookedTrue(date);

        // Verifica quais horários ainda não estão reservados
        for (LocalTime time : availableTimes) {
            boolean isAlreadyBooked = bookedSlots.stream()
                    .anyMatch(slot -> slot.getAvailableTime().equals(time));

            if (!isAlreadyBooked) {
                TimeSlot slot = new TimeSlot();
                slot.setAppointmentDate(date);
                slot.setAvailableTime(time);
                slot.setBooked(false); // O horário não está reservado
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
    public void  bookTimeSlot(String data , String time) throws IllegalStateException {
        // Converte a string "data" para LocalDate
        LocalDate appointmentDate = LocalDate.parse(data);

        // Converte a string "time" para LocalTime
        LocalTime appointmentTime = LocalTime.parse(time);

        TimeSlot timeSlot = timeSlotRepository.findByAppointmentDateAndAvailableTime(appointmentDate, appointmentTime);

        if( timeSlot == null) {
            TimeSlot newTimeSlot = new TimeSlot();
            newTimeSlot.setAppointmentDate(appointmentDate);
            newTimeSlot.setAvailableTime(appointmentTime);
            newTimeSlot.setBooked(true);  // Marca o horário como reservado
            timeSlotRepository.save(newTimeSlot);
        }

      else {
            throw new IllegalStateException("Horário já reservado");
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
}
