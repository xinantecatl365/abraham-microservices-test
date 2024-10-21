package com.abraham.creditservice.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreditResponse {
    private Integer creditId;
    private double creditAmount;
    private float interestRate;
    private String startDate;
    private String endDate;
    private CreditStatus status;
    private Integer customerId;
}
