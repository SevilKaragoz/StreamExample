package com.streambank;

public class Customer {
    String customerId;
    String name;
    boolean active;
    String segment;

    public Customer(String customerId, String name, boolean active, String segment) {
        this.customerId = customerId;
        this.name = name;
        this.active = active;
        this.segment = segment;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }
}
