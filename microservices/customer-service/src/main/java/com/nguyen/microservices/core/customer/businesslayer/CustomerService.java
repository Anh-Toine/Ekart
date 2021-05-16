package com.nguyen.microservices.core.customer.businesslayer;

import com.nguyen.api.core.customer.Customer;

public interface CustomerService {
    Customer findByCustomerId(int customerId);
    Customer addCustomer(Customer model);
    void deleteCustomer(int customerId);
}
