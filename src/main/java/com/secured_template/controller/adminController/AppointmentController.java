package com.secured_template.controller.adminController;

import com.secured_template.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AppointmentController {

private final TimeSlotService timeSlotService;

    public AppointmentController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    //Torna o horário de um barbeiro indisponível sem criar um agendamento (ADMIN only)
    @PostMapping("/close-timeslot")
    public ResponseEntity<Void> turnTimeSlotBooked(
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("barberId") Long barberId) {

        timeSlotService.bookTimeSlot(date, time, barberId);
        return ResponseEntity.ok().build();
    }
}
