package com.secured_template.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private Integer id;
    private Date appointmentDate;
    private Time appointmentTime;
    private String customerName;
    private String professionalName;
    private String serviceName;
    private BigDecimal servicePrice;
}
