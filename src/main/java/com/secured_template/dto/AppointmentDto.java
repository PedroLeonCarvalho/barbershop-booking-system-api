package com.secured_template.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Long customerId;
    private Long professionalId;
    private Long serviceId;
}
