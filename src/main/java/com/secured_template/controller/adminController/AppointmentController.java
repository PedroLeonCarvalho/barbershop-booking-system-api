package com.secured_template.controller.adminController;

import com.secured_template.domain.User;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.dto.UserDto;
import com.secured_template.service.AppointmentService;
import com.secured_template.service.TimeSlotService;
import com.secured_template.service.UserService;
import org.springframework.http.HttpStatus;
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
private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, TimeSlotService timeSlotService, UserService userService) {
        this.appointmentService = appointmentService;
        this.timeSlotService = timeSlotService;
        this.userService = userService;
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

    //Torna o horário de um barbeiro disponível, porém não desmarca um agendamento já feito. (ADMIN only)
    @PostMapping("/open-timeslot")
    public ResponseEntity<Void> turnTimeSlotBooked(
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            Authentication authentication) {

        var barber = (User) authentication.getPrincipal();
        var barberId = barber.getId();

        timeSlotService.setFreeTimeSlot(date, time, barberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/close-days-schedule")
    public ResponseEntity<String> turnTimeSlotBooked(
            @RequestParam("date") String date,
            Authentication authentication) {

        User barber = (User) authentication.getPrincipal();
        var barberId = barber.getId();

        timeSlotService.turnAllTimeSlotBookedTrue(date, barberId);
        return ResponseEntity.ok().body("Agenda fechada para o dia" + date);
    }

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


    @PostMapping
    ResponseEntity<UserDto> createNewStaffUser (@RequestBody UserDto user) {
        var newUser = userService.createStaffUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }



}
