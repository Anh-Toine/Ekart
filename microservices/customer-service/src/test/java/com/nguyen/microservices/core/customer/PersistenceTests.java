package com.nguyen.microservices.core.customer;

import com.nguyen.microservices.core.customer.datalayer.CustomerEntity;
import com.nguyen.microservices.core.customer.datalayer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PersistenceTests {
    @Autowired
    private CustomerRepository repository;
    private CustomerEntity entity;
    @BeforeEach
    public void setupDb(){
        repository.deleteAll();
        CustomerEntity customerEntity = new CustomerEntity(1,"Edward","Tarrakovitch","112-334-0405","edward333@gmail.com","Almond",35,"J8D 2H6","Quebec")
    }
}
