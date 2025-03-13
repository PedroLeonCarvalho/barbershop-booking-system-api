package com.secured_template.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record UserDto (
     Long id,
     String name,
     String email,
     String phoneNumber,
     LocalDate birthDate,
     String password,
     byte[] profile_picture

)  implements Serializable { }
