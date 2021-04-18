package com.nguyen.microservices.core.item.presentation.controllers;

import com.nguyen.api.core.item.Item;
import com.nguyen.api.core.item.ItemServiceAPI;
import com.nguyen.utils.exceptions.EmptyCartException;
import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.exceptions.NotFoundException;
import com.nguyen.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemRESTController implements ItemServiceAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRESTController.class);
    private final ServiceUtil serviceUtil;

    public ItemRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    public List<Item> getItems(int customerId){
        List<Item> items = new ArrayList<>();
        if(customerId < 1) throw new InvalidInputException("Invalid customer ID: "+customerId);
        else if(customerId == 72) throw new EmptyCartException("Customer "+customerId+" has an empty item list");
        else if(customerId == 102) {
            LOGGER.debug("No items found for customer with ID: {}",customerId);
            return items;
        }
        items.add(new Item(
           customerId,
           1,
           "Calculus 101: Pocket Edition",
            11.00,
            1,
            serviceUtil.getServiceAddress()
        ));
        items.add(new Item(
                customerId,
                2,
                "Bicycle: Acropolis (Blue Edition)",
                9.00,
                1,
                serviceUtil.getServiceAddress()
        ));
        items.add(new Item(
                customerId,
                3,
                "Pfizer Hand Sanitizer (1oz)",
                4.00,
                4,
                serviceUtil.getServiceAddress()
        ));
        LOGGER.debug("Response size for /item: {}",items.size());
        return items;
    }
}
