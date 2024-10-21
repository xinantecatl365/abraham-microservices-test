package com.abraham.customer.models;

public record CustomerRequest(
        String firstName, String lastName, String email, String phone, String address, String curp
) {
}
