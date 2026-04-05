package com.framework.model;

/**
 * Represents a bank account with deposit, withdrawal, and transfer operations.
 */
public class BankAccount {

    private String accountId;
    private String owner;
    private double balance;
    private boolean frozen;

    public BankAccount(String accountId, String owner, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account ID cannot be null or blank");
        }
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner cannot be null or blank");
        }
        this.accountId = accountId;
        this.owner = owner;
        this.balance = initialBalance;
        this.frozen = false;
    }

    public void deposit(double amount) {
        if (frozen) throw new IllegalStateException("Account is frozen");
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
    }

    public void withdraw(double amount) {
        if (frozen) throw new IllegalStateException("Account is frozen");
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient funds");
        balance -= amount;
    }

    public void transfer(BankAccount target, double amount) {
        if (target == null) throw new IllegalArgumentException("Target account cannot be null");
        this.withdraw(amount);
        target.deposit(amount);
    }

    public void freeze() { this.frozen = true; }
    public void unfreeze() { this.frozen = false; }

    public double getBalance() { return balance; }
    public String getAccountId() { return accountId; }
    public String getOwner() { return owner; }
    public boolean isFrozen() { return frozen; }
}
