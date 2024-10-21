package com.abraham.customer.models;

import com.abraham.customer.entities.Customer;

public class CustomerMapper {


    public static Customer fromCustomerRequest(CustomerRequest customerRequest) {
        return Customer.builder()
                .address(customerRequest.address())
                .email(customerRequest.email())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .phone(customerRequest.phone())
                .curp(customerRequest.curp())
                .maxCredit(6000f)
                .build();
    }
}
