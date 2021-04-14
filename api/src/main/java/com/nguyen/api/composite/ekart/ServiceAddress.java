package com.nguyen.api.composite.ekart;

public class ServiceAddress {
    private String ekartAddress;
    private String customerAddress;
    private String itemAddress;
    private String supplierAddress;

    public ServiceAddress(String ekartAddress, String customerAddress, String itemAddress, String supplierAddress) {
        this.ekartAddress = ekartAddress;
        this.customerAddress = customerAddress;
        this.itemAddress = itemAddress;
        this.supplierAddress = supplierAddress;
    }

    public ServiceAddress() {
        this.ekartAddress = null;
        this.customerAddress = null;
        this.itemAddress = null;
        this.supplierAddress = null;
    }

    public String getEkartAddress() {
        return ekartAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }
}
