package com.secured_template.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record UserDto (

     String name,
     String email,
     String phoneNumber,
     LocalDate birthDate,
     String password

)  implements Serializable { }
