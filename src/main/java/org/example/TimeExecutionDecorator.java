package org.example;

import java.time.Duration;
import java.time.Instant;

public class TimeExecutionDecorator implements TimeExecution{

    TimeExecution timeExecution;

    TimeExecutionDecorator(TimeExecution timeExecution){
        this.timeExecution = timeExecution;
    }

    @Override
    public Object Execute() {
        Instant start = Instant.now();
        Object objet = timeExecution.Execute();
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        Long hours = duration.toHours();
        Long minutes = duration.toMinutes() % 60;
        Long secondes = duration.getSeconds() % 60;
        System.out.println("Temps d'execution: "+ hours+ " heures, "+minutes+", minutes "+secondes+" secondes");
        return objet;
    }
}
