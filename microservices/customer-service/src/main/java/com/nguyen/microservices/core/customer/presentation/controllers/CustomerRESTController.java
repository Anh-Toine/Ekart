package com.nguyen.microservices.core.customer.presentation.controllers;

import com.nguyen.api.core.customer.Customer;
import com.nguyen.api.core.customer.CustomerServiceAPI;
import com.nguyen.microservices.core.customer.businesslayer.CustomerService;
import com.nguyen.utils.exceptions.EmptyCartException;
import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerRESTController implements CustomerServiceAPI{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRESTController.class);

    private final CustomerService service;

    public CustomerRESTController(CustomerService service) {
        this.service = service;
    }

    @Override
    public Customer getCustomer(int customerId){
        LOGGER.debug("Found customer with ID: "+customerId);
        if(customerId < 1){
            throw new InvalidInputException("Invalid ID: "+customerId);
        }


        /*
        if(customerId == 103){
            throw new NotFoundException("No customer found for ID: "+customerId);
        }

         */
        return service.findByCustomerId(customerId);
    }

    @Override
    public Customer addCustomer(Customer model) {
        Customer customer = service.addCustomer(model);
        LOGGER.debug("REST addCustomer: entity created for customerId: {}",model.getCustomerId());
        return customer;
    }

    @Override
    public void deleteCustomer(int customerId) {
        LOGGER.debug("REST deleteCustomer entity deleted for customerId: {}",customerId);
        service.deleteCustomer(customerId);
    }
}
