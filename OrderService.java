package org.example;

public class OrderService {
    public String placeOrder(boolean paymentStatus) {
        if (paymentStatus) return "Order Placed";
        else return "Payment Failed";
    }
}
