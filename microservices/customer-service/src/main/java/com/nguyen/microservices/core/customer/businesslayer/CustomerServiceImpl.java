package com.nguyen.microservices.core.customer.businesslayer;

import com.mongodb.DuplicateKeyException;
import com.nguyen.api.core.customer.Customer;
import com.nguyen.microservices.core.customer.datalayer.CustomerEntity;
import com.nguyen.microservices.core.customer.datalayer.CustomerRepository;
import com.nguyen.utils.exceptions.InvalidInputException;
import com.nguyen.utils.exceptions.NotFoundException;
import com.nguyen.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerServiceImpl implements CustomerService{
    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository repo;
    private final CustomerMapper mapper;
    private final ServiceUtil serviceUtil;

    public CustomerServiceImpl(CustomerRepository repo, CustomerMapper mapper, ServiceUtil serviceUtil) {
        this.repo = repo;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Customer findByCustomerId(int customerId) {
        CustomerEntity entity = repo.findByCustomerId(customerId).
                orElseThrow(() -> new NotFoundException("No customer found for ID: "+customerId));
        Customer customer = mapper.entityToModel(entity);
        customer.setServiceAddress(serviceUtil.getServiceAddress());
        LOG.debug("Found customer with ID: {}",customerId);
        return customer;
    }

    @Override
    public Customer addCustomer(Customer model) {
        try{
            CustomerEntity entity = mapper.modelToEntity(model);
            CustomerEntity newCustomer = repo.save(entity);
            LOG.debug("[CustomerServiceImpl] addCustomer: created new customer with ID: {}",model.getCustomerId());
            return mapper.entityToModel(newCustomer);
        }catch(DuplicateKeyException dke){
            throw new InvalidInputException("Duplicate key, customerId: "+model.getCustomerId());
        }

    }

    @Override
    public void deleteCustomer(int customerId) {
        LOG.debug("[CustomerServiceImpl] deleteCustomer: deleted customer with ID: {}",customerId);
        repo.findByCustomerId(customerId).ifPresent(ce -> repo.delete(ce));
    }
}
