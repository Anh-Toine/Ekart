package com.nguyen.microserices.composite.ekart.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyen.api.core.customer.Customer;
import com.nguyen.api.core.customer.CustomerServiceAPI;
import com.nguyen.api.core.item.Item;
import com.nguyen.api.core.item.ItemServiceAPI;
import com.nguyen.api.core.supplier.Supplier;
import com.nguyen.api.core.supplier.SupplierServiceAPI;
import com.nguyen.utils.exceptions.EmptyCartException;
import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.exceptions.NotFoundException;
import com.nguyen.utils.http.EkartErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
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
        try {
            String url = customerServiceUrl + customerId;
            LOGGER.debug("Calling getCustomer API on URL: " + url);

            Customer customer = restTemplate.getForObject(url, Customer.class);
            LOGGER.debug("Found a customer with ID: " + customer.getCustomerId());
            return customer;
        } catch (HttpClientErrorException e) {
            HttpStatus status = e.getStatusCode();
            String responsebody = e.getResponseBodyAsString();
            switch (status) {
                case NOT_FOUND:
                    throw new NotFoundException(getError(e));
                case UNPROCESSABLE_ENTITY:
                    throw new InvalidInputException(getError(e));
                case FORBIDDEN:
                    throw new EmptyCartException(getError(e));
                default:
                    LOGGER.debug("Unexpected HTTP status returned: {}, rethrowing it.", status);
                    LOGGER.warn("[WARNING]| Found error body: {}", responsebody);
                    throw e;
            }
        }
    }

    private String getError(HttpClientErrorException e) {
        try {
            return objectMapper.readValue(e.getResponseBodyAsString(), EkartErrorInfo.class).getMessage();
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public List<Item> getItems(int customerId) {
        try {
            String url = itemServiceUrl + customerId;
            LOGGER.debug("Calling getItems API on URL:" + url);

            List<Item> items = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Item>>() {
                    }).getBody();
            int listsize = items.size();

            LOGGER.debug("Returning {} items for customer with ID: {}", listsize, customerId);
            return items;
        } catch (Exception e) {
            LOGGER.debug("[EXCEPTION]| Got an exception while querying items, returning 0 items");
            LOGGER.debug("[EXCEPTION]| Error message: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Supplier> getSuppliers(int customerId) {
        try {
            String url = supplierServiceUrl + customerId;
            LOGGER.debug("Calling getSuppliers API on URL:" + url);

            List<Supplier> suppliers = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Supplier>>() {
                    }).getBody();
            int listsize = suppliers.size();

            LOGGER.debug("Returning {} items for customer with ID: {}", listsize, customerId);
            return suppliers;
        } catch (Exception e) {
            LOGGER.debug("[EXCEPTION]| Got an exception while querying items, returning 0 items");
            LOGGER.debug("[EXCEPTION]| Error message: {}", e.getMessage());
            return new ArrayList<>();
        }
    }
}

