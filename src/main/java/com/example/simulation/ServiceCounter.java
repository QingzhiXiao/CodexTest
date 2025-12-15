package com.example.simulation;

/**
 * Represents a service counter that can serve one customer at a time.
 */
public class ServiceCounter {
    private Customer currentCustomer;
    private int remainingServiceTime;

    /**
     * Assigns the provided customer to this counter.
     *
     * @param customer customer to serve
     */
    public void assignCustomer(Customer customer) {
        this.currentCustomer = customer;
        this.remainingServiceTime = customer.getServiceDuration();
    }

    /**
     * Decrements the remaining time by one unit.
     *
     * @return true if the customer just finished and departs in this tick
     */
    public boolean tick() {
        if (currentCustomer == null) {
            return false;
        }
        remainingServiceTime--;
        if (remainingServiceTime <= 0) {
            currentCustomer = null;
            return true;
        }
        return false;
    }

    /**
     * Indicates whether the counter is currently free to take a new customer.
     *
     * @return true if no customer is being served
     */
    public boolean isFree() {
        return currentCustomer == null;
    }
}
