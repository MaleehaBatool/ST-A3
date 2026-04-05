package org.example;

public class PaymentService {
    public boolean processPayment(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        return amount < 10000;
    }
}