package com.abraham.creditservice.models;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateNewCreditRequest {
    private int customerId;
    private double amount;
    private float interestRate;
    private String endDate;
}
