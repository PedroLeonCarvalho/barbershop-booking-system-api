package com.secured_template.controller.userController;

import com.secured_template.domain.TimeSlot;
import com.secured_template.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserTimeSlotController {

public final     TimeSlotService timeSlotService;

    public UserTimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/available-schedules")
    public ResponseEntity<List<TimeSlot>> getAvaibleTimeSlots(@RequestParam ("date") String date) {

        LocalDate appointmentDate = LocalDate.parse(date); // Converte a string para LocalDate
        List<TimeSlot> schedules = timeSlotService.getAvailableTimeSlots(appointmentDate);
        return ResponseEntity.ok(schedules);
    }


}
