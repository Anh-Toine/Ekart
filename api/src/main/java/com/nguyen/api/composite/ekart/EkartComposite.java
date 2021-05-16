package com.nguyen.api.composite.ekart;

import com.nguyen.api.core.item.Item;
import com.nguyen.api.core.supplier.Supplier;

import java.util.List;

public class EkartComposite {
    private final int customerId;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
    private final String streetName;
    private final int houseNumber;
    private final String zipCode;
    private final String province;
    private final List<ItemSummary> items;
    private final List<SupplierSummary> suppliers;
    private final ServiceAddress serviceAddress;

    public EkartComposite(int customerId, String firstName, String lastName, String phoneNumber, String email, String streetName, int houseNumber, String zipCode, String province, List<ItemSummary> items, List<SupplierSummary> suppliers, ServiceAddress serviceAddress) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.province = province;
        this.items = items;
        this.suppliers = suppliers;
        this.serviceAddress = serviceAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getProvince() {
        return province;
    }

    public List<ItemSummary> getItems() {
        return items;
    }

    public List<SupplierSummary> getSuppliers() {
        return suppliers;
    }

    public ServiceAddress getServiceAddress() {
        return serviceAddress;
    }
}
