package com.secured_template.controller.userController;

import com.secured_template.dto.ServiceResponseDto;
import com.secured_template.service.BarberServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class BarberServiceUserController {

private final BarberServiceService serviceService;

    public BarberServiceUserController(BarberServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponseDto>> getAllServices() {
        List<ServiceResponseDto> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }
}
