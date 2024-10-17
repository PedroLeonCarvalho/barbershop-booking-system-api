package com.secured_template.controller.adminController;

import com.secured_template.domain.User;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.service.AppointmentService;
import com.secured_template.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AppointmentController {
private final AppointmentService appointmentService;

private final TimeSlotService timeSlotService;

    public AppointmentController(AppointmentService appointmentService, TimeSlotService timeSlotService) {
        this.appointmentService = appointmentService;
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
//    @PostMapping("/close-days-schedule")
//    public ResponseEntity<Void> turnTimeSlotBooked(
//            @RequestParam("date") String date,
//            @RequestParam("time") String time,
//            @RequestParam("barberId") Long barberId) {
//
//        timeSlotService.bookTimeSlot(date, time, barberId);
//        return ResponseEntity.ok().build();
//    }
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getMyAppointmentsBarbarber(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        var apponitment = appointmentService.getMyAppointmentsBarbarber(user.getId());
        return ResponseEntity.ok().body(apponitment);
    }

    @GetMapping("/appointments/today")
    public ResponseEntity<List<AppointmentResponseDto>> getMyCurrentDayAppointmentsBarbarber(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        var apponitment = appointmentService.getMyCurrentDayBarberAppointments(user.getId());
        return ResponseEntity.ok().body(apponitment);
    }

    @GetMapping("/appointments/{date}")
    public ResponseEntity<List<AppointmentResponseDto>> getMyEspecificDayAppointmentsBarbarber(Authentication authentication, @PathVariable("date") LocalDate date) {
        User user = (User) authentication.getPrincipal();
        var apponitment = appointmentService.getMyEspecificDayBarberAppointments(user.getId(), date);
        return ResponseEntity.ok().body(apponitment);
    }



}
