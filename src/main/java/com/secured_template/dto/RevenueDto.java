package com.secured_template.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@Setter
public class RevenueDto implements Serializable {

    private BigDecimal totalOfTheDay;
    private Integer numberOfCostomers;
    private BigDecimal avaragePricePerCostumer;
}
