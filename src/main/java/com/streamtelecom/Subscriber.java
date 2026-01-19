package com.streamtelecom;

public class Subscriber {
    String subscriberId;
    String segment; // PREPAID, POSTPAID, CORPORATE
    boolean active;

    public Subscriber(String subscriberId, String segment, boolean active) {
        this.subscriberId = subscriberId;
        this.segment = segment;
        this.active = active;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
