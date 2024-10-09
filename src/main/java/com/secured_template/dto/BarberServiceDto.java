package com.secured_template.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberServiceDto implements Serializable {

        private String name;

        private Integer durationMinutes;

        private BigDecimal price;

        private String description;

        private Boolean isActive = true;
    }

