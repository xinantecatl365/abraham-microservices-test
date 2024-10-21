package com.abraham.creditservice.models;

import com.abraham.creditservice.entities.Credit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditMapper {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Credit fromNewCreditRequest(CreateNewCreditRequest request, CreditStatus status) {
        return Credit.builder()
                .amount(request.getAmount())
                .interestRate(request.getInterestRate())
                .status(status)
                .endDate(LocalDate.parse(request.getEndDate(), formatter))
                .customerId(request.getCustomerId())
                .build();
    }


    public static CreditResponse fromCredit(Credit credit) {
        return CreditResponse.builder()
                .creditId(credit.getId())
                .creditAmount(credit.getAmount())
                .interestRate(credit.getInterestRate())
                .startDate(credit.getStartDate().toString())
                .endDate(credit.getEndDate().toString())
                .status(credit.getStatus())
                .customerId(credit.getCustomerId())
                .build();
    }
}
