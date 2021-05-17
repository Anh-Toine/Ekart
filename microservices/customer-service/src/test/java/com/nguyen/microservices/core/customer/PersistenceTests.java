package com.nguyen.microservices.core.customer;

import com.nguyen.api.core.customer.Customer;
import com.nguyen.microservices.core.customer.datalayer.CustomerEntity;
import com.nguyen.microservices.core.customer.datalayer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PersistenceTests {
    @Autowired
    private CustomerRepository repository;
    private CustomerEntity entity;
    @BeforeEach
    public void setupDb(){
        repository.deleteAll();
        CustomerEntity customerEntity = new CustomerEntity(1,"AA","BB","111-111-1111","CC","DD",1,"EE","FF");
        entity = repository.save(customerEntity);
        assertThat(entity, samePropertyValuesAs(customerEntity));
    }

    @Test
    public void addCustomerEntity(){
        CustomerEntity newEntity = new CustomerEntity(2,"AAA","BBB","222-222-2222","CCC","DDD",2,"EEE","FFF");
        repository.save(newEntity);
        CustomerEntity found = repository.findByCustomerId(newEntity.getCustomerId()).get();
        assertThat(found,samePropertyValuesAs(newEntity));
        assertEquals(2,repository.count());
    }

    @Test
    public void updateCustomerEntity(){
        entity.setFirstName("II");
        repository.save(entity);

        CustomerEntity found = repository.findByCustomerId(entity.getCustomerId()).get();
        assertEquals(1,(long)found.getVersion());
        assertEquals("II",found.getFirstName());
    }

    @Test
    public void deleteCustomerEntity(){
        repository.delete(entity);
        assertFalse(repository.existsById(entity.getCustomerId()));
    }

    @Test
    public void getCustomerEntity(){
        Optional<CustomerEntity> customerEntityOptional = repository.findByCustomerId(entity.getCustomerId());
        assertTrue(customerEntityOptional.isPresent());
        assertThat(customerEntityOptional.get(), samePropertyValuesAs(entity));
    }

}
