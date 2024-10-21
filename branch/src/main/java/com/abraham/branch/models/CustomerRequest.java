package com.abraham.branch.models;

public record CustomerRequest(
        String firstName,
        String lastName,
        String curp,
        String email,
        String phoneNumber
) {
}
