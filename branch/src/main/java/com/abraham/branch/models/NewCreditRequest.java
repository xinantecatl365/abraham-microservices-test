package com.abraham.branch.models;

public record NewCreditRequest(
        int customerId,
        float amount
) {
}
