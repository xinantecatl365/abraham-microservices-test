package com.abraham.customer.customers;

import com.abraham.customer.models.CreateCustomerResponse;
import com.abraham.customer.models.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> saveNewCustomer(
            @RequestBody CustomerRequest request
    ) {
        final var body = customerService.createNewCustomer(request);
        return ResponseEntity.ok(body);
    }

    // eventually update the max credit in this user
//    @PostMapping
//    public ResponseEntity<CreateCustomerResponse> updateCustomer(
//            @RequestBody CustomerRequest request
//    ){
//
//    }

    @GetMapping("/enough-credit")
    public ResponseEntity<Boolean> hasEnoughCredit(
            @RequestParam double amount, @RequestParam int customerId
    ) {
        Boolean result = customerService.hasEnoughCredit(amount, customerId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerResponse> getCustomer(@PathVariable int id) {
        var body = customerService.findById(id);
        return ResponseEntity.ok(CreateCustomerResponse.builder().customerId(body.getId()).build());
    }

    @PutMapping("/increase-credit/{id}")
    public ResponseEntity<CreateCustomerResponse> increaseMaxCredit(
            @PathVariable int id,
            @RequestParam float amount
    ) {
        var body = customerService.increaseMaxCredit(id, amount);
        return ResponseEntity.ok(body);
    }


}
