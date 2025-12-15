package com.example.simulation;

import java.util.Random;

/**
 * Runs a discrete-time simulation of customers arriving and being served by counters.
 */
public class Simulation {
    private final int simulationTime;
    private final double arrivalRate;
    private final int serviceTime;
    private final int numCounters;
    private final Random random;

    public Simulation(int simulationTime, double arrivalRate, int serviceTime, int numCounters, long seed) {
        if (simulationTime <= 0 || serviceTime <= 0 || numCounters <= 0) {
            throw new IllegalArgumentException("Simulation time, service time, and counters must be positive");
        }
        if (arrivalRate < 0) {
            throw new IllegalArgumentException("Arrival rate cannot be negative");
        }
        this.simulationTime = simulationTime;
        this.arrivalRate = arrivalRate;
        this.serviceTime = serviceTime;
        this.numCounters = numCounters;
        this.random = new Random(seed);
    }

    public SimulationResult run() {
        MyQueue<Customer> queue = new ArrayQueue<>();
        ServiceCounter[] counters = new ServiceCounter[numCounters];
        for (int i = 0; i < numCounters; i++) {
            counters[i] = new ServiceCounter();
        }

        int nextCustomerId = 1;
        int maxQueueLength = 0;
        int totalServed = 0;
        long totalWaitingTime = 0;

        for (int currentTime = 0; currentTime < simulationTime; currentTime++) {
            int arrivals = samplePoisson(arrivalRate);
            for (int i = 0; i < arrivals; i++) {
                queue.enqueue(new Customer(nextCustomerId++, currentTime, serviceTime));
            }

            maxQueueLength = Math.max(maxQueueLength, queue.size());

            for (ServiceCounter counter : counters) {
                if (!counter.isFree()) {
                    boolean finished = counter.tick();
                    if (finished) {
                        totalServed++;
                    }
                }

                if (counter.isFree() && !queue.isEmpty()) {
                    Customer next = queue.dequeue();
                    int waitingTime = currentTime - next.getArrivalTime();
                    totalWaitingTime += waitingTime;
                    counter.assignCustomer(next);
                }
            }
        }

        // Customers still in service at the end are not counted as served.
        double averageWaitingTime = totalServed == 0 ? 0.0 : (double) totalWaitingTime / totalServed;
        return new SimulationResult(totalServed, averageWaitingTime, maxQueueLength);
    }

    /**
     * Samples from a Poisson distribution using the Knuth algorithm.
     */
    private int samplePoisson(double lambda) {
        double l = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;
        do {
            k++;
            p *= random.nextDouble();
        } while (p > l);
        return k - 1;
    }
}
