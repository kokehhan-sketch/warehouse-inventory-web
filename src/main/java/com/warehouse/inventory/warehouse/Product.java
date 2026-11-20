package com.warehouse.inventory.warehouse;

public class Product {

    private String id; //Product id
    private String name; //Product name
    private int quantity; //Product quantity
    private double price; //Product price
    private String type; //Product Type
    private int minStock; //Minimum stock level

    public Product(String id, String name, int quantity, double price, String type, int minStock) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.price = price;
        this.minStock = minStock;
    }

    public Product() {
    
    }

    // Return
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getMinStock() {
        return minStock;
    }

    public String getType() {
        return type;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    //LowStockChecker
    public boolean isLowStock() {
        return quantity < minStock;
    }

    // Display
    public String toString() {
        return id + " | " + name + " | Qty: " + quantity + " | Price: " + price + " | Type: " + type;
    }
}
