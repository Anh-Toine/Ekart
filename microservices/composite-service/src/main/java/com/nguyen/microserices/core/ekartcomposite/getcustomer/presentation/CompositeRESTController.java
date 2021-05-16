package com.nguyen.microserices.core.ekartcomposite.getcustomer.presentation;

import com.nguyen.api.composite.ekart.*;
import com.nguyen.api.core.customer.Customer;
import com.nguyen.api.core.item.Item;
import com.nguyen.api.core.supplier.Supplier;
import com.nguyen.microserices.core.ekartcomposite.getcustomer.integration.EkartCompositeIntegration;
import com.nguyen.utils.exceptions.NotFoundException;
import com.nguyen.utils.http.ServiceUtil;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompositeRESTController implements EkartCompositeServiceAPI {
    private final EkartCompositeIntegration integration;
    private final ServiceUtil serviceUtil;

    public CompositeRESTController(EkartCompositeIntegration integration, ServiceUtil serviceUtil) {
        this.integration = integration;
        this.serviceUtil = serviceUtil;
    }


    @Override
    public EkartComposite getCustomer(int customerId) {
        Customer customer = integration.getCustomer(customerId);
        if(customer == null) throw new NotFoundException("Customer non-existent: " + customerId);
        List<Item> items = integration.getItems(customerId);
        List<Supplier> suppliers = integration.getSuppliers(customerId);
        return initiateEkartComposite(customer,items,suppliers,serviceUtil.getServiceAddress());
    }

    private EkartComposite initiateEkartComposite(Customer customer, List<Item> items, List<Supplier> suppliers, String serviceAddress) {
        int customerId = customer.getCustomerId();
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String phoneNumber = customer.getPhoneNumber();
        String email = customer.getEmail();
        String streetName = customer.getStreetName();
        int houseNumber = customer.getHouseNumber();
        String zipCode= customer.getZipCode();
        String province = customer.getProvince();

        List<ItemSummary> itemSummaries = (items == null) ? null :
            items.stream()
                .map(i -> new ItemSummary(i.getItemId(),i.getItemName(),i.getItemPrice(),i.getItemQuantity()))
                .collect(Collectors.toList());

        List<SupplierSummary> supplierSummaries = (suppliers == null) ? null :
                suppliers.stream()
                        .map(s -> new SupplierSummary(s.getSupplierId(),s.getSupplierName(),s.getSupplierSite(),s.getLocation()))
                        .collect(Collectors.toList());

        String customerAddress = customer.getServiceAddress();

        String itemAddress = (items != null && items.size() > 0) ?
                items.get(0).getServiceAddress() : "";
        String supplierAddress = (suppliers != null && suppliers.size() > 0) ?
                suppliers.get(0).getServiceAddress() : "";
        ServiceAddress address = new ServiceAddress(serviceAddress,customerAddress,itemAddress,supplierAddress);
        return new EkartComposite(customerId, firstName, lastName, phoneNumber, email, streetName, houseNumber, zipCode, province,itemSummaries,supplierSummaries,address);
    }

}
