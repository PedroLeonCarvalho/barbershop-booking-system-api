package com.secured_template.controller.userController;

import com.secured_template.domain.TimeSlot;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.TimeSlotDto;
import com.secured_template.service.AppointmentService;
import com.secured_template.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AppointmentController {

private final TimeSlotService timeSlotService;

    public AppointmentController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

//Reserva um TimeSlot mas não cria um Appointment
    // Ideias: esse pode ser um endmpoint para tornar indisponível um horário
    // o timeSlotService.bookTimeSlot deve ser chamado dentro de marcar appointment para indisponibilizar o timeSlot
    @PostMapping("/book")
    public ResponseEntity<Void> turnTimeSlotBooked(
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("barberId") Long barberId) {

        // Chama o serviço para reservar o horário com base na data e horário
        timeSlotService.bookTimeSlot(date, time, barberId);

        return ResponseEntity.ok().build();
    }

    //Pegar os dados do estado do front-end
//    @PostMapping("/create-appointment")
//    public ResponseEntity<String> bookTimeSlot(@RequestBody AppointmentDto appointmentDto) {
//       var dateStr = appointmentDto.getAppointmentDate().toString();
//       var timeStr =   appointmentDto.getAppointmentTime().toString();
//        timeSlotService.bookTimeSlot(dateStr,timeStr);
//
//        return ResponseEntity.ok().body("Agendamento concluído com sucesso!");
//    }

}
