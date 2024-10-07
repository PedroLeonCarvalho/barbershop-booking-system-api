package com.secured_template.controller;

import com.secured_template.dto.UserDto;
import com.secured_template.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<UserDto> createNewUser (@RequestBody UserDto user) {
        var newUser =userService.createUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }
   @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/acaoRestrita")
    ResponseEntity<String> acaoRestrita () {
        return new ResponseEntity<String>("Ação restrita ao ADM realizada com sucesso", HttpStatus.CREATED); }

}
