package com.nguyen.microservices.core.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebClient
class CustomerServiceApplicationTests {
	private static final int CUSTOMER_ID_VALID = 5;
	private static final int CUSTOMER_ID_NEGATIVE = -8;
	private static final int CUSTOMER_ID_NOT_FOUND = 103;
	private static final int CUSTOMER_ID_EMPTY_CART= 72;
	private static final String CUSTOMER_ID_STRING = "customer-id";

	@Autowired
	private WebTestClient client;
	@Test
	public void findCustomerById(){
		client.get()
				.uri("/customer/"+CUSTOMER_ID_VALID)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.customerId").isEqualTo(CUSTOMER_ID_VALID);
	}
	@Test
	public void findCustomerNotFound(){
		client.get()
				.uri("/customer/"+CUSTOMER_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/customer/"+CUSTOMER_ID_NOT_FOUND)
				.jsonPath("$.message").isEqualTo("No customer found for ID: "+CUSTOMER_ID_NOT_FOUND);
	}
	@Test
	public void findCustomerNegativeId(){
		client.get()
				.uri("/customer/"+CUSTOMER_ID_NEGATIVE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/customer/"+CUSTOMER_ID_NEGATIVE)
				.jsonPath("$.message").isEqualTo("Invalid ID: "+CUSTOMER_ID_NEGATIVE);
	}

	@Test
	void contextLoads() {
	}

}
