package com.nguyen.microservices.core.customer.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity,Integer> {

    Optional<CustomerEntity> findByCustomerId(int customerId);
}
