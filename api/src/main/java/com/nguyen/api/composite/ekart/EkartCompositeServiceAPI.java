package com.nguyen.api.composite.ekart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface EkartCompositeServiceAPI {
    @GetMapping(
            value = "/ekart/{customerId}",
            produces = "application/json"
    )
    EkartComposite getCustomer(@PathVariable int customerId);
}
