package com.secured_template.controller.unauthenticated;

import com.secured_template.domain.TimeSlot;
import com.secured_template.dto.ServiceResponseDto;
import com.secured_template.service.BarberServiceService;
import com.secured_template.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/info")
public class PublicInfoController {
    private final TimeSlotService timeSlotService;

private final BarberServiceService serviceService;

    public PublicInfoController(TimeSlotService timeSlotService, BarberServiceService serviceService) {
        this.timeSlotService = timeSlotService;
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceResponseDto>> getAllServices() {
        List<ServiceResponseDto> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/timeslots")
    public ResponseEntity<List<TimeSlot>> getAvaibleTimeSlots(@RequestParam("date") String date,
                                                              @RequestParam("barberId") Long barberId ) {
        LocalDate appointmentDate = LocalDate.parse(date); // Converte a string para LocalDate
        List<TimeSlot> schedules = timeSlotService.getAvailableTimeSlots(appointmentDate, barberId);
        return ResponseEntity.ok(schedules);
    }





}
