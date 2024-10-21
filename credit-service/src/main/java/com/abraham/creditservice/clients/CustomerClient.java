package com.abraham.creditservice.clients;

import com.abraham.creditservice.models.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "customers", url = "http://localhost:8072")
public interface CustomerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/customers/enough-credit")
    boolean hasEnoughCredit(@RequestParam double amount, @RequestParam int customerId);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/customers/{id}")
    CustomerResponse getCustomer(@PathVariable int id);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/customers/increase-credit/{id}")
    CustomerResponse increaseMaxCredit(@PathVariable int id, @RequestParam float amount);

}
