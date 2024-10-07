package com.secured_template.dto;

import java.io.Serializable;

public record LoginDTO(
        String email,
        String password
) implements Serializable {


}