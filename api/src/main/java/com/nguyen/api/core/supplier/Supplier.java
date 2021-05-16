package com.nguyen.api.core.supplier;

public class Supplier {
    private  int supplierId;
    private  int customerId;
    private  String supplierName;
    private  String supplierSite;
    private  String location;
    private  String serviceAddress;

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

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setSupplierSite(String supplierSite) {
        this.supplierSite = supplierSite;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
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
