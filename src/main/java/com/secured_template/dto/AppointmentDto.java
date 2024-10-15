package com.secured_template.dto;


import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentDto {

    private Integer id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Long serviceId;
    private Long customerId;
    private String customerName;
    private Long professionalId;

}
