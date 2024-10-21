package com.secured_template.controller.unauthenticated;

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

    @PostMapping ("/create")
    ResponseEntity<UserDto> createNewUser (@RequestBody UserDto user) {
        var newUser =userService.createUser(user);
        return new ResponseEntity(newUser, HttpStatus.CREATED);
    }

}
