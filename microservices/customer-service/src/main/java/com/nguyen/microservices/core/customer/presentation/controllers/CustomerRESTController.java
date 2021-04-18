package com.nguyen.microservices.core.customer.presentation.controllers;

import com.nguyen.api.core.customer.Customer;
import com.nguyen.api.core.customer.CustomerServiceAPI;
import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.exceptions.NotFoundException;
import com.nguyen.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerRESTController implements CustomerServiceAPI{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRESTController.class);

    private final ServiceUtil serviceUtil;

    public CustomerRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public Customer getCustomer(int customerId){
        LOGGER.debug("Found customer with ID: "+customerId);
        if(customerId < 1){
            throw new InvalidInputException("Invalid ID: "+customerId);
        }else if(customerId == 101){
            throw new NotFoundException("No customer found for ID: "+customerId);
        }
        return new Customer(customerId,"Jimmy","Alsworth","1234567890","jimmyal@aol.com","Milk St.",185,"T5A 7H3","Alberta",serviceUtil.getServiceAddress());
    }
}
