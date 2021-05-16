package com.nguyen.api.core.item;

public class Item {
    private  int itemId;
    private  int customerId;
    private  String itemName;
    private  double itemPrice;
    private  int itemQuantity;
    private  String serviceAddress;

    public Item(int customerId,int itemId, String itemName, double itemPrice, int itemQuantity, String serviceAddress) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.serviceAddress = serviceAddress;
    }

    public Item() {
        this.customerId = 0;
        this.itemId = 0;
        this.itemName = null;
        this.itemPrice = 0.0;
        this.itemQuantity = 0;
        this.serviceAddress = null;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
}
