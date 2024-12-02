package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // 1. lire les donnés a partir d'un fichier data.csv
        TimeExecution timeExecution;

        // Add the description for the read operation
        timeExecution = new TimeExecutionDecorator(new ReadFile("data.csv"), "Lire les données depuis data.csv");
        List<String> list = (List<String>) timeExecution.Execute();

        // 2. Traitement de données
        timeExecution = new TimeExecutionDecorator(new Process(list), "Traitement des données");
        timeExecution.Execute();

        // 3. Enregistrer les données dans un nouveau fichier data_out.csv
        timeExecution = new TimeExecutionDecorator(new WriteToFile(list, "data_out.csv"), "Enregistrer les données dans data_out.csv");
        timeExecution.Execute();
    }
}
