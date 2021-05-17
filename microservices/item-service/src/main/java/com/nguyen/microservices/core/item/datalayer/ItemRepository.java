package com.nguyen.microservices.core.item.datalayer;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity,String> {
}
