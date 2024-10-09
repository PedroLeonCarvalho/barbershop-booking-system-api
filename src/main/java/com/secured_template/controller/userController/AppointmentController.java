package com.secured_template.controller.userController;

import com.secured_template.service.AppointmentService;
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
