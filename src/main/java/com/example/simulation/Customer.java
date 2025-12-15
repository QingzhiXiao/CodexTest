package com.example.simulation;

/**
 * Represents a customer in the simulation with an arrival time and required service duration.
 */
public class Customer {
    private final int id;
    private final int arrivalTime;
    private final int serviceDuration;

    public Customer(int id, int arrivalTime, int serviceDuration) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceDuration = serviceDuration;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceDuration() {
        return serviceDuration;
    }
}
