package com.secured_template.controller.userController;

import com.secured_template.domain.User;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.AppointmentResponseDto;
import com.secured_template.infra.exception.AppointmentTimeUnavailableException;
import com.secured_template.service.AppointmentService;
import com.secured_template.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class BookingController {

private final TimeSlotService timeSlotService;
private final AppointmentService appointmentService;


    public BookingController(TimeSlotService timeSlotService, AppointmentService appointmentService) {
        this.timeSlotService = timeSlotService;
        this.appointmentService = appointmentService;
    }

//    Pegar os dados do estado do front-end
    @PostMapping("/create-appointment")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentDto appointmentDto, Authentication auth) throws AppointmentTimeUnavailableException {
       var dateStr = appointmentDto.getAppointmentDate().toString();
       var timeStr =   appointmentDto.getAppointmentTime().toString();
       var barberId = appointmentDto.getProfessionalId();
       User u = (User) auth.getPrincipal();
       appointmentDto.setCustomerId(u.getId());

        timeSlotService.bookTimeSlot(dateStr,timeStr,barberId);
        appointmentService.createAppointment(appointmentDto);


        return ResponseEntity.ok().body("Agendamento concluído com sucesso!");
    }

    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Serviço desativado com sucesso"); // Retorna um 204 No Content
    }

    @GetMapping("/appointment")
    public ResponseEntity<AppointmentResponseDto> getMyAppointments(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
       var apponitment = appointmentService.getMyAppointments(user.getId());
        return ResponseEntity.ok().body(apponitment);
    }



}
