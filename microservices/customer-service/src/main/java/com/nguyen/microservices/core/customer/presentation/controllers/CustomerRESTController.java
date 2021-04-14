package com.nguyen.microservices.core.customer.presentation.controllers;

import com.nguyen.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRESTController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRESTController.class);

    private final ServiceUtil serviceUtil;

    public CustomerRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }
}
