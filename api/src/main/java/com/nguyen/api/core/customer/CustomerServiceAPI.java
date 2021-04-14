package com.nguyen.api.core.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CustomerServiceAPI {
    @GetMapping(
            value = "/customer/{customerId}",
            produces = "application/json"
    )
    Customer getCustomer(@PathVariable int customerId);
}
