package com.abraham.creditservice.models;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatisticsModel {
    private BigDecimal mean;
    private BigDecimal median;
    private BigDecimal mode;
}
