package com.nguyen.api.composite.ekart;

public class SupplierSummary {
    private final int supplierId;
    private final String supplierName;
    private final String supplierSite;
    private final String location;

    public SupplierSummary(int supplierId, String supplierName, String supplierSite, String location) {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierSite = supplierSite;
        this.location = location;
    }

    public SupplierSummary() {
        this.supplierId = 0;
        this.supplierName = null;
        this.supplierSite = null;
        this.location = null;
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
}
