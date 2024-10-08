package com.secured_template.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public class ServiceResponseDto {

    private String name;
    private Integer durationMinutes;
    private BigDecimal price;
    private String description;

}
