package com.secured_template.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer id;
    private Date appointmentDate;
    private Time appointmentTime;
    private Long customerId;
    private Long professionalId;
    private Long serviceId;
}
