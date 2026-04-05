package com.framework.service;

import com.framework.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Shopping cart service that manages items, quantities, and totals.
 */
public class ShoppingCart {

    private final Map<Product, Integer> items = new HashMap<>();
    private double discountPercent = 0;

    public void addItem(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (!product.isAvailable(quantity)) throw new IllegalStateException("Insufficient stock for: " + product.getName());
        items.merge(product, quantity, Integer::sum);
    }

    public void removeItem(Product product) {
        if (!items.containsKey(product)) throw new IllegalArgumentException("Product not in cart");
        items.remove(product);
    }

    public void updateQuantity(Product product, int newQuantity) {
        if (!items.containsKey(product)) throw new IllegalArgumentException("Product not in cart");
        if (newQuantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        items.put(product, newQuantity);
    }

    public double getSubtotal() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public double getTotal() {
        double subtotal = getSubtotal();
        return subtotal - (subtotal * discountPercent / 100);
    }

    public void applyDiscount(double percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Discount must be between 0 and 100");
        this.discountPercent = percent;
    }

    public void clearCart() { items.clear(); }

    public int getItemCount() { return items.values().stream().mapToInt(Integer::intValue).sum(); }

    public boolean isEmpty() { return items.isEmpty(); }

    public Map<Product, Integer> getItems() { return new HashMap<>(items); }
}
