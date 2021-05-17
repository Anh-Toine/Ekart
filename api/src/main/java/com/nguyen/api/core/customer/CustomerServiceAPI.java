package com.nguyen.api.core.customer;

import org.springframework.web.bind.annotation.*;

public interface CustomerServiceAPI {
    @GetMapping(
            value = "/customer/{customerId}",
            produces = "application/json"
    )
    Customer getCustomer(@PathVariable int customerId);

    @PostMapping(
            value = "/customer/",
            consumes = "application/json",
            produces = "application/json"
    )
    Customer addCustomer(@RequestBody Customer model);

    @DeleteMapping(
            value = "/customer/{customerId}"
    )
    void deleteCustomer(@PathVariable int customerId);
}
