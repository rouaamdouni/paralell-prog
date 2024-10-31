package org.example;

import java.util.ArrayList;
import java.util.List;

public class Process implements TimeExecution{

    private  List<String> list;

    Process(List<String> list){
        this.list = list;
    }


    @Override
    public List Execute()  {
        List<Thread> threads = new ArrayList<>();
        int numThread = Runtime.getRuntime().availableProcessors();
        int partie = list.size()/numThread;
        for (int i = 0; i <numThread ; i++) {
            int start = (partie*i)+1;
            int end = (i+1)==numThread?numThread:(partie*i)+partie;
            Thread thread = new Thread(()->{
                for(int j=start;j<end;j++){
                    String fields[] =list.get(j).split(",");
                    String date = Utils.convertDate(fields[6]);
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
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }
}
