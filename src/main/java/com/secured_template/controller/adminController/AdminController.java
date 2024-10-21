package com.secured_template.controller.adminController;

import com.secured_template.domain.User;
import com.secured_template.dto.AppointmentDto;
import com.secured_template.dto.RevenueDto;
import com.secured_template.dto.UserDto;
import com.secured_template.service.AppointmentService;
import com.secured_template.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final AppointmentService appointmentService;

    public AdminController(UserService userService, AppointmentService appointmentService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<UserDto> createNewStaffUser (@RequestBody UserDto user) {
        var newUser = userService.createStaffUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

  }
