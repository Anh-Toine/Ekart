package com.nguyen.api.core.supplier;

public class Supplier {
    private int supplierId;
    private int customerId;
    private String supplierName;
    private String supplierSite;
    private String location;
    private String serviceAddress;

    public Supplier(int customerId,int supplierId, String supplierName, String supplierSite, String location, String serviceAddress) {
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierSite = supplierSite;
        this.location = location;
        this.serviceAddress = serviceAddress;
    }

    public Supplier() {
        this.customerId = 0;
        this.supplierId = 0;
        this.supplierName = null;
        this.supplierSite = null;
        this.location = null;
        this.serviceAddress = null;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierSite() {
        return supplierSite;
    }

    public String getLocation() {
        return location;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
}
