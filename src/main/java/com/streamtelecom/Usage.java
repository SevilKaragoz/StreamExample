package com.streamtelecom;

import java.time.LocalDate;

public class Usage {
        String subscriberId;
        String type;   // DATA, VOICE, SMS
        double amount;

    public Usage(String subscriberId, String type, double amount) {
        this.subscriberId = subscriberId;
        this.type = type;
        this.amount = amount;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

