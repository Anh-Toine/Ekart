package com.nguyen.microservices.core.customer.businesslayer;

import com.nguyen.api.core.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerServiceImpl implements CustomerService{
    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Customer findByCustomerId(int customerId) {
        return null;
    }

    @Override
    public Customer addCustomer(Customer model) {
        return null;
    }

    @Override
    public void deleteCustomer(int customerId) {

    }
}
