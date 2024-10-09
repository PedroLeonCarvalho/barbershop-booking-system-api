package com.secured_template.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Builder
@Getter
@Setter
public class ServiceResponseDto implements Serializable {

    private String name;
    private Integer durationMinutes;
    private BigDecimal price;
    private String description;

}
