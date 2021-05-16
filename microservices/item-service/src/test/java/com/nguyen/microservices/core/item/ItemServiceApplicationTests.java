package com.nguyen.microservices.core.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ItemServiceApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebClient
class ItemServiceApplicationTests {
	private static final int CUSTOMER_ID_VALID = 5;
	private static final int CUSTOMER_ID_NEGATIVE = -8;
	private static final int CUSTOMER_ID_NOT_FOUND = 103;
	private static final int CUSTOMER_ID_EMPTY_CART= 72;
	private static final String CUSTOMER_ID_STRING = "customer-id";

	@Autowired
	private WebTestClient client;

	@Test
	public void findItemsWithCustomerId(){
		
		final int epxectedLength = 3;
		client.get()
				.uri("/item?customerId="+CUSTOMER_ID_VALID)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.length()").isEqualTo(epxectedLength)
				.jsonPath("$[0].customerId").isEqualTo(CUSTOMER_ID_VALID)
				.jsonPath("$[1].customerId").isEqualTo(CUSTOMER_ID_VALID)
				.jsonPath("$[2].customerId").isEqualTo(CUSTOMER_ID_VALID);
	}

	@Test
	public void findItemsWithNegativeCustomerId(){
		client.get()
				.uri("/item?customerId="+CUSTOMER_ID_NEGATIVE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/item")
				.jsonPath("$.message").isEqualTo("Invalid customer ID: "+CUSTOMER_ID_NEGATIVE);
	}

	@Test
	public void findItemsCustomerNotFound(){
		final int expectedLength = 0;
		client.get()
				.uri("/item?customerId="+CUSTOMER_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.length()").isEqualTo(expectedLength);
	}

	@Test
	public void findItemsEmptyList(){
		client.get()
				.uri("/item?customerId="+CUSTOMER_ID_EMPTY_CART)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.FORBIDDEN)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/item")
				.jsonPath("$.message").isEqualTo("Customer "+CUSTOMER_ID_EMPTY_CART+" has an empty item list");
	}

	@Test
	public void findItemsMissingParameter(){
		client.get()
				.uri("/item")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isBadRequest()
				.expectBody()
				.jsonPath("$.path").isEqualTo("/item")
				.jsonPath("$.message").isEqualTo("Required int parameter 'customerId' is not present");
	}

	@Test
	public void findItemsInvalidParameter(){
		client.get()
				.uri("/item?customerId="+CUSTOMER_ID_STRING)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/item")
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}

	@Test
	void contextLoads() {
	}

}
