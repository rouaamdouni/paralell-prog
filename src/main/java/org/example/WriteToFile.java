package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile implements TimeExecution{
    List<String> list;
    String path;

    WriteToFile(List<String> list, String path){
        this.list = list;
        this.path = path;
    }

    @Override
    public Object Execute() {
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
        return null;
    }

}
