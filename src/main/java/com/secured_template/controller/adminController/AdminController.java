package com.secured_template.controller.adminController;

import com.secured_template.dto.UserDto;
import com.secured_template.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<UserDto> createNewStaffUser (@RequestBody UserDto user) {
        var newUser = userService.createStaffUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }
}
