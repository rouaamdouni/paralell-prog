package org.example;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // 1. lire les donnés a partir d'un fichier data.csv
        List<String> list = readFiles("data.csv");
        // 2. Traitement de données
        Instant start = Instant.now();
        //<String> list = Arrays.asList("1,2,3");
        list = process(list);
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);
        Long hours = duration.toHours();
        Long minutes = duration.toMinutes() % 60;
        Long secondes = duration.getSeconds() % 60;
        System.out.println("Temps d'execution: "+ hours+ " heures, "+minutes+", minutes "+secondes+" secondes");
        // 3. Enregistrer les données dans un nouveau fichier data_out.csv
        //writeToFile(list,"data_out.csv");
    }

    private static void writeToFile(List<String> list, String path) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for(String element:list){
                bufferedWriter.write(element);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> process(List<String> list) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        int numThread = Runtime.getRuntime().availableProcessors();
        int partie = list.size()/numThread;
        for (int i = 0; i <numThread ; i++) {
            int start = (partie*i)+1;
            int end = (i+1)==numThread?numThread:(partie*i)+partie;
            Thread thread = new Thread(()->{
                for(int j=start;j<end;j++){
                    String fields[] =list.get(j).split(",");
                    String date = convertDate(fields[6]);
                    fields[6] = date;
                    list.set(j,String.join(",",fields));
                }
            });
            threads.add(thread);
        }


        for(Thread thread:threads){
            thread.start();
        }

        for(Thread thread:threads){
            thread.join();
        }

        return list;
    }

    private static String convertDate(String field) {
        Pattern pattern = Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})");
        Matcher matcher = pattern.matcher(field);
        if(matcher.matches()){
            String year = matcher.group(1);
            String month = matcher.group(2);
            String day = matcher.group(3);
            field = matcher.group(3)+"/"+matcher.group(2)+"/"+matcher.group(1);
            return field;
        }
        return field;
    }

    private static List<String> readFiles(String path) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while((line = bufferedReader.readLine())!=null){
                list.add(line);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}