package com.streambank;

public class Account {
    String accountNo;
    String customerId;
    double balance;
    boolean blocked;

    public Account(String accountNo, String customerId, double balance, boolean blocked) {
        this.accountNo = accountNo;
        this.customerId = customerId;
        this.balance = balance;
        this.blocked = blocked;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
