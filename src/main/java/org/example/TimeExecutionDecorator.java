package org.example;

import java.time.Duration;
import java.time.Instant;

public class TimeExecutionDecorator implements TimeExecution {

    TimeExecution timeExecution;
    String taskName;

    // Constructor now accepts a task name
    TimeExecutionDecorator(TimeExecution timeExecution, String taskName) {
        this.timeExecution = timeExecution;
        this.taskName = taskName;
    }

    @Override
    public Object Execute() {
        Instant start = Instant.now();
        Object objet = timeExecution.Execute();
        Instant end = Instant.now();
        
        // Calculate the duration
        Duration duration = Duration.between(start, end);

        // Display the duration in milliseconds
        long milliseconds = duration.toMillis();  // Total duration in milliseconds
        System.out.println(taskName + " - Temps d'execution: " + milliseconds + " milliseconds");

        return objet;
    }
}
