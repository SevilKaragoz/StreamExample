package com.streambank;

import java.time.LocalDate;

public class Transaction {
    String accountNo;
    double amount;
    String type;   // DEPOSIT, WITHDRAW, TRANSFER

    public Transaction(String accountNo, double amount, String type ) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.type = type;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
