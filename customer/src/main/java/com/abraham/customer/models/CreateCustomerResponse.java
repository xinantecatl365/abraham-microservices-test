package com.abraham.customer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateCustomerResponse {
    private Integer customerId;
}
