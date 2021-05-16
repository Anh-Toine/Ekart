package com.nguyen.microservices.core.customer.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {
    @Transactional(readOnly = true)
    List<CustomerEntity> findByCustomerId(int customerId);
}
