package com.nguyen.microservices.core.supplier.presentation.controllers;

import com.nguyen.api.core.supplier.Supplier;
import com.nguyen.api.core.supplier.SupplierServiceAPI;
import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.exceptions.EmptyCartException;
import com.nguyen.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SupplierRESTController implements SupplierServiceAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierRESTController.class);
    private final ServiceUtil serviceUtil;

    public SupplierRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public List<Supplier> getSuppliers(int customerId){
        List<Supplier> suppliers = new ArrayList<>();
        if(customerId < 1) throw new InvalidInputException("Invalid customer ID: "+customerId);
        else if(customerId == 72) throw new EmptyCartException("Customer "+customerId+" has an empty item list");
        else if(customerId == 103){
            LOGGER.debug("No suppliers found for customer with ID: {}",customerId);
            return suppliers;
        }
        suppliers.add(new Supplier(
                customerId,
                1,
                "Supplier 1",
                "Site 1",
                "Location 1",
                serviceUtil.getServiceAddress()
        ));
        suppliers.add(new Supplier(
                customerId,
                2,
                "Supplier 2",
                "Site 2",
                "Location 2",
                serviceUtil.getServiceAddress()
        ));
        suppliers.add(new Supplier(
                customerId,
                3,
                "Supplier 3",
                "Site 3",
                "Location 3",
                serviceUtil.getServiceAddress()
        ));
        return suppliers;
    }
}
