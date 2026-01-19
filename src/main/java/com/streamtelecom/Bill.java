package com.streamtelecom;

public class Bill {
    String subscriberId;
    double totalAmount;
    boolean paid;

    public Bill(String subscriberId, double totalAmount, boolean paid) {
        this.subscriberId = subscriberId;
        this.totalAmount = totalAmount;
        this.paid = paid;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
