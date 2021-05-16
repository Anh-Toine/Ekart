package com.nguyen.microservices.core.ekartcomposite;


import com.nguyen.api.core.customer.Customer;
import com.nguyen.api.core.item.Item;
import com.nguyen.api.core.supplier.Supplier;
import com.nguyen.microserices.core.ekartcomposite.CompositeServiceApplication;
import com.nguyen.microserices.core.ekartcomposite.getcustomer.integration.EkartCompositeIntegration;
import com.nguyen.utils.exceptions.EmptyCartException;
import com.nguyen.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CompositeServiceApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebClient
class CompositeServiceApplicationTests {
    private static final int CUSTOMER_ID_VALID = 5;
    private static final int CUSTOMER_ID_NEGATIVE = -8;
    private static final int CUSTOMER_ID_NOT_FOUND = 103;
    private static final int CUSTOMER_ID_EMPTY_CART= 72;
    private static final String CUSTOMER_ID_STRING = "customer-id";

    @Autowired
    private WebTestClient client;

    @MockBean
    private EkartCompositeIntegration integration;

    @BeforeEach
    public void setup(){
        when(integration.getCustomer(CUSTOMER_ID_VALID))
                .thenReturn(new Customer(CUSTOMER_ID_VALID,"Jimmy","Alsworth","1234567890","jimmyal@aol.com","Milk St.",185,"T5A 7H3","Alberta","Mock address"));
        when(integration.getItems(CUSTOMER_ID_VALID))
                .thenReturn(Collections.singletonList(new Item(CUSTOMER_ID_VALID,1,"Mock item",5.55,1,"Mock address")));
        when(integration.getSuppliers(CUSTOMER_ID_VALID))
                .thenReturn(Collections.singletonList(new Supplier(CUSTOMER_ID_VALID,1,"Mock supplier","Mock site","Mock location","Mock address")));
        when(integration.getCustomer(CUSTOMER_ID_NOT_FOUND))
                .thenThrow(new NotFoundException("CUSTOMER NOT FOUND: "+CUSTOMER_ID_NOT_FOUND));
        when(integration.getCustomer(CUSTOMER_ID_NEGATIVE))
                .thenThrow(new NotFoundException("INVALID ID: "+CUSTOMER_ID_NEGATIVE));
        when(integration.getCustomer(CUSTOMER_ID_EMPTY_CART))
                .thenThrow(new EmptyCartException("EMPTY LIST: "+CUSTOMER_ID_EMPTY_CART));
    }

    @Test
    public void findCustomerById(){
        final int expectedResponseSize = 1;
        client.get()
                .uri("/ekart/"+CUSTOMER_ID_VALID)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.customerId").isEqualTo(CUSTOMER_ID_VALID)
                .jsonPath("$.items.length()").isEqualTo(expectedResponseSize)
                .jsonPath("$.suppliers.length()").isEqualTo(expectedResponseSize);
    }

    @Test
    public void findCustomerNotFound(){
        client.get()
                .uri("/ekart/"+CUSTOMER_ID_NOT_FOUND)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/ekart/"+CUSTOMER_ID_NOT_FOUND)
                .jsonPath("$.message").isEqualTo("CUSTOMER NOT FOUND: "+CUSTOMER_ID_NOT_FOUND);
    }
    @Test
    public void findCustomerNegativeId(){
        client.get()
                .uri("/ekart/"+CUSTOMER_ID_NEGATIVE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/ekart/"+CUSTOMER_ID_NEGATIVE)
                .jsonPath("$.message").isEqualTo("INVALID ID: "+CUSTOMER_ID_NEGATIVE);
    }
    @Test
    public void findCustomerWithEmptyCart(){
        client.get()
                .uri("/ekart/"+CUSTOMER_ID_EMPTY_CART)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/ekart/"+CUSTOMER_ID_EMPTY_CART)
                .jsonPath("$.message").isEqualTo("EMPTY LIST: "+CUSTOMER_ID_EMPTY_CART);
    }
    @Test
    public void findCustomerStringParameter(){
        client.get()
                .uri("/ekart/"+CUSTOMER_ID_STRING)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/ekart/"+CUSTOMER_ID_STRING)
                .jsonPath("$.message").isEqualTo("Type mismatch.");
    }

    @Test
    void contextLoads() {
    }
}
