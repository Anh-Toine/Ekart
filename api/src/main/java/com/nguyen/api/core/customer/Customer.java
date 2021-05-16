package com.nguyen.api.core.customer;

public class
Customer {
    private  int customerId;
    private  String firstName;
    private  String lastName;
    private  String phoneNumber;
    private  String email;
    private  String streetName;
    private  int houseNumber;
    private  String zipCode;
    private  String province;
    private  String serviceAddress;

    public Customer(int customerId, String firstName, String lastName, String phoneNumber, String email, String streetName, int houseNumber, String zipCode, String province, String serviceAddress) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.province = province;
        this.serviceAddress = serviceAddress;
    }

    public Customer() {
        this.customerId = 0;
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber = null;
        this.email = null;
        this.streetName = null;
        this.houseNumber = 0;
        this.zipCode = null;
        this.province = null;
        this.serviceAddress = null;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setServiceAddress(String serviceAddress) {
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

    public String getServiceAddress() {
        return serviceAddress;
    }
}
