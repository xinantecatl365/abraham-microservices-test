package com.abraham.customer.customers;

import com.abraham.customer.entities.Customer;
import com.abraham.customer.models.CustomerMapper;
import com.abraham.customer.models.CreateCustomerResponse;
import com.abraham.customer.models.CustomerRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;

    @Transactional
    public CreateCustomerResponse createNewCustomer(CustomerRequest request) {
        final Integer id = repository.save(CustomerMapper.fromCustomerRequest(request)).getId();

        return CreateCustomerResponse.builder()
                .customerId(id)
                .build();
    }

    public Boolean hasEnoughCredit(double amount, int customerId) {
        final var customer = repository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return amount <= customer.getMaxCredit();
    }

    public Customer findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
//        return customer;
    }

    public CreateCustomerResponse increaseMaxCredit(int id, float amount) {
        final var customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setMaxCredit(customer.getMaxCredit() + amount);
        repository.save(customer);
        return CreateCustomerResponse.builder()
                .customerId(customer.getId())
                .build();

    }
}
