package com.secured_template.controller;

import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

//    @PostMapping
//    ResponseEntity <AppointmentResponseDto> createAppointment (@RequestBody AppointmentDto appointmentDto) {
//        var response = appointmentService.createAppointment (appointmentDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

}
