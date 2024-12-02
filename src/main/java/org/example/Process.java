package org.example;

import java.util.ArrayList;
import java.util.List;

public class Process implements TimeExecution {

    private final List<String> list;

    public Process(List<String> list) {
        this.list = list;
    }

    @Override
    public List<String> Execute() {
        List<Thread> threads = new ArrayList<>();
        int numThreads = Runtime.getRuntime().availableProcessors(); // Number of threads
        System.out.println("Number of threads: " + numThreads);

        int partitionSize = list.size() / numThreads; // Portion size for each thread

        // Create and assign threads
        for (int i = 0; i < numThreads; i++) {
            int start = partitionSize * i;
            int end = (i == numThreads - 1) ? list.size() : start + partitionSize; // Handle last partition

            Thread thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    String[] fields = list.get(j).split(",");
                    String date = Utils.convertDate(fields[6]); // Assuming convertDate exists in Utils
                    fields[6] = date;
                    list.set(j, String.join(",", fields)); // Update the list with the modified value
                }
            });

            threads.add(thread);
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread execution interrupted", e);
            }
        }

        return list; // Return the processed list
    }
}
