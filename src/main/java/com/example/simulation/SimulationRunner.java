package com.example.simulation;

import java.util.Locale;

/**
 * Entry point that demonstrates the queue-based customer service simulation
 * using several scenarios described in the project brief.
 */
public class SimulationRunner {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        int simulationTime = 1000;
        int serviceTime = 3;

        System.out.println("=== Counter comparison (arrival rate = 0.3) ===");
        runScenario(simulationTime, serviceTime, 0.3, 1, 42L);
        runScenario(simulationTime, serviceTime, 0.3, 2, 42L);
        runScenario(simulationTime, serviceTime, 0.3, 3, 42L);

        System.out.println();
        System.out.println("=== Arrival rate comparison (2 counters) ===");
        runScenario(simulationTime, serviceTime, 0.1, 2, 99L);
        runScenario(simulationTime, serviceTime, 0.3, 2, 99L);
        runScenario(simulationTime, serviceTime, 0.5, 2, 99L);
    }

    private static void runScenario(int simulationTime, int serviceTime, double arrivalRate, int counters, long seed) {
        Simulation simulation = new Simulation(simulationTime, arrivalRate, serviceTime, counters, seed);
        SimulationResult result = simulation.run();
        System.out.printf(
                Locale.US,
                "Counters=%d, ArrivalRate=%.2f -> Served=%d, AvgWait=%.2f, MaxQueue=%d%n",
                counters,
                arrivalRate,
                result.getTotalServed(),
                result.getAverageWaitingTime(),
                result.getMaxQueueLength());
    }
}
