package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        // 1. lire les donnés a partir d'un fichier data.csv
        TimeExecution timeExecution;

        timeExecution = new TimeExecutionDecorator(new ReadFile("data.csv"));
        List<String> list = (List<String>) timeExecution.Execute();

        // 2. Traitement de données

        timeExecution  = new TimeExecutionDecorator(new Process(list));
        timeExecution.Execute();

        // 3. Enregistrer les données dans un nouveau fichier data_out.csv


        timeExecution = new TimeExecutionDecorator(new WriteToFile(list,"data_out.csv"));
        timeExecution.Execute();
    }








}