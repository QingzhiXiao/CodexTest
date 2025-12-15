package com.example.simulation;

/**
 * Aggregated metrics produced by a simulation run.
 */
public class SimulationResult {
    private final int totalServed;
    private final double averageWaitingTime;
    private final int maxQueueLength;

    public SimulationResult(int totalServed, double averageWaitingTime, int maxQueueLength) {
        this.totalServed = totalServed;
        this.averageWaitingTime = averageWaitingTime;
        this.maxQueueLength = maxQueueLength;
    }

    public int getTotalServed() {
        return totalServed;
    }

    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public int getMaxQueueLength() {
        return maxQueueLength;
    }

    @Override
    public String toString() {
        return String.format("Served=%d, AvgWait=%.2f, MaxQueue=%d", totalServed, averageWaitingTime, maxQueueLength);
    }
}
