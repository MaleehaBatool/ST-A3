package com.framework.model;

/**
 * Represents a product in an e-commerce system.
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product(String id, String name, double price, int stock) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (quantity > stock) throw new IllegalStateException("Not enough stock");
        stock -= quantity;
    }

    public void restockBy(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        stock += quantity;
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Discount must be between 0 and 100");
        price = price - (price * percent / 100);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}
