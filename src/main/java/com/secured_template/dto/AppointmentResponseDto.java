package com.secured_template.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private Integer id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String customerName;
    private String professionalName;
    private String serviceName;
    private BigDecimal servicePrice;
    private String comments;
}
