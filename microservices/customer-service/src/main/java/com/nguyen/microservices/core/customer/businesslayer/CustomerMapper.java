package com.nguyen.microservices.core.customer.businesslayer;

import com.nguyen.api.core.customer.Customer;
import com.nguyen.microservices.core.customer.datalayer.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "serviceAddress",ignore = true)
    Customer entityToModel(CustomerEntity customerEntity);
    @Mappings({
          @Mapping(target = "id",ignore = true),
            @Mapping(target = "version",ignore = true)
    })
    CustomerEntity modelToEntity(Customer customer);
}
