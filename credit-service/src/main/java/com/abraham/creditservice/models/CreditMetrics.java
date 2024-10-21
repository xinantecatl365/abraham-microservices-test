package com.abraham.creditservice.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreditMetrics {
    private Long count;
    private CreditStatus status;
}
