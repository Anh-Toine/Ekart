package com.nguyen.microservices.core.customer;

import com.nguyen.api.core.customer.Customer;
import com.nguyen.microservices.core.customer.datalayer.CustomerEntity;
import com.nguyen.microservices.core.customer.datalayer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static reactor.core.publisher.Mono.just;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CustomerServiceApplication.class, properties = {"spring.data.mongodb.port: 0"})
@ExtendWith(SpringExtension.class)
@AutoConfigureWebClient
@AutoConfigureWebTestClient(timeout = "10000")//10 seconds
class CustomerServiceApplicationTests {
	private static final int CUSTOMER_ID_VALID = 5;
	private static final int CUSTOMER_ID_NEGATIVE = -8;
	private static final int CUSTOMER_ID_NOT_FOUND = 103;
	private static final int CUSTOMER_ID_EMPTY_CART= 72;
	private static final String CUSTOMER_ID_STRING = "customer-id";

	@Autowired
	private WebTestClient client;

	@Autowired
	private CustomerRepository repository;

	@BeforeEach
	public void setupDb(){
		repository.deleteAll();
	}

	@Test
	public void findCustomerById(){
		CustomerEntity entity = new CustomerEntity(CUSTOMER_ID_VALID,"firstname","lastname","phonenumber","email","streetName",1,"zipCode","province");
		repository.save(entity);
		assertTrue(repository.findByCustomerId(CUSTOMER_ID_VALID).isPresent());
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
	public void addCustomer(){
		Customer customer = new Customer(CUSTOMER_ID_VALID,"firstname","lastname","phonenumber","email","streetname",1,"zipcode","province","sa");
		client.post()
				.uri("/customer")
				.body(just(customer),Customer.class)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.customerId").isEqualTo(CUSTOMER_ID_VALID);

		assertTrue(repository.findByCustomerId(CUSTOMER_ID_VALID).isPresent());
	}

	@Test
	public void deleteCustomer(){
		CustomerEntity entity = new CustomerEntity(CUSTOMER_ID_VALID,"firstname","lastname","phonenumber","email","streetName",1,"zipCode","province");
		repository.save(entity);
		assertTrue(repository.findByCustomerId(CUSTOMER_ID_VALID).isPresent());
		client.delete()
				.uri("/customer/"+CUSTOMER_ID_VALID)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody();
		assertFalse(repository.findByCustomerId(CUSTOMER_ID_VALID).isPresent());
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
	public void findCustomerWithEmptyCart(){
		client.get()
				.uri("/customer/"+CUSTOMER_ID_EMPTY_CART)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.FORBIDDEN)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/customer/"+CUSTOMER_ID_EMPTY_CART)
				.jsonPath("$.message").isEqualTo("Customer "+CUSTOMER_ID_EMPTY_CART+" has an empty item list");
	}
	@Test
	public void findCustomerStringParameter(){
		client.get()
				.uri("/customer/"+CUSTOMER_ID_STRING)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/customer/"+CUSTOMER_ID_STRING)
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}
	@Test
	void contextLoads() {
	}

}
