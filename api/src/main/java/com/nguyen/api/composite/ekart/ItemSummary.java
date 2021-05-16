package com.nguyen.api.composite.ekart;

public class ItemSummary {
    private final int itemId;
    private final String itemName;
    private final double itemPrice;
    private final int itemQuantity;

    public ItemSummary(int itemId, String itemName, double itemPrice, int itemQuantity) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public ItemSummary(){
        this.itemId = 0;
        this.itemName = null;
        this.itemPrice = 0.0;
        this.itemQuantity = 0;
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
}
