# CodexTest

This repository contains a simple queue-based customer service simulation written in Java. It demonstrates how a custom Queue ADT can be used to model real-world waiting lines and includes sample scenarios comparing different numbers of counters and arrival rates.

## Running the simulation

Compile and run using the Java compiler included with the JDK:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.example.simulation.SimulationRunner
```

The output prints summary statistics for each scenario, including the total customers served, average waiting time, and maximum queue length observed.
