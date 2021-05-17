package com.nguyen.microservices.core.item.datalayer;

import javax.persistence.*;

@Entity
@Table(name = "item",indexes = @Index(name = "item_unique_index",
    unique = true, columnList = "customerId,itemId"))
public class ItemEntity {
    @Id
    @GeneratedValue
    private int id;
    @Version
    private int version;
    private  int itemId;
    private  int customerId;
    private  String itemName;
    private  double itemPrice;
    private  int itemQuantity;

    public ItemEntity(int itemId, int customerId, String itemName, double itemPrice, int itemQuantity) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public ItemEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
