package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile implements TimeExecution{

    String path;

    ReadFile(String path){
        this.path = path;
    }

    @Override
    public Object Execute() {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
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
