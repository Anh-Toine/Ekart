package com.nguyen.microserices.composite.ekart.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyen.api.core.customer.Customer;
import com.nguyen.api.core.customer.CustomerServiceAPI;
import com.nguyen.api.core.item.Item;
import com.nguyen.api.core.item.ItemServiceAPI;
import com.nguyen.api.core.supplier.Supplier;
import com.nguyen.api.core.supplier.SupplierServiceAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class EkartCompositeIntegration implements CustomerServiceAPI, ItemServiceAPI, SupplierServiceAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(EkartCompositeIntegration.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String customerServiceUrl;
    private final String itemServiceUrl;
    private final String supplierServiceUrl;

    public EkartCompositeIntegration(
            RestTemplate restTemplate,
            ObjectMapper objectMapper,

            @Value("${app.customer-service.host}") String customerServiceHost,
            @Value("${app.customer-service.port}") String customerServicePort,

            @Value("${app.item-service.host}") String itemServiceHost,
            @Value("${app.item-service.port}") String itemServicePort,

            @Value("${app.supplier-service.host}") String supplierServiceHost,
            @Value("${app.supplier-service.port}") String supplierServicePort
    ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        customerServiceUrl = "http://" + customerServiceHost + ":" + customerServicePort + "/customer/";
        itemServiceUrl = "http://" + itemServiceHost + ":" + itemServicePort + "/item?customerId=";
        supplierServiceUrl = "http://" + supplierServiceHost + ":" + supplierServicePort + "/supplier?customerId=";
    }

    @Override
    public Customer getCustomer(int customerId) {
        try{
            String url = customerServiceUrl + customerId;
            LOGGER.debug("Calling getCustomer API on URL: "+url);

            Customer customer = restTemplate.getForObject(url, Customer.class);
            LOGGER.debug("Found a customer with ID: "+customer.getId());
            return customer;
        }catch(HttpClientErrorException e){
            switch(e.getStatusCode()){
                case NOT_FOUND:

            }
        }
    }

    @Override
    public List<Item> getItems(int customerId) {
        return null;
    }

    @Override
    public List<Supplier> getSuppliers(int customerId) {
        return null;
    }
}
