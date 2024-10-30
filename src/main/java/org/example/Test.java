package org.example;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        /*int threadNum = 4;
        int nombreLigne = 106;
        int partie = nombreLigne/threadNum;
        System.out.println(partie);
        // 1--25  26-50 51-75 76-100
        for (int i = 0; i < threadNum; i++) {
            int start = (partie*i)+1;
            int end = (i+1)==threadNum?nombreLigne:(partie*i)+partie;
            //int end = (partie*i)+partie;
            System.out.print("Thread "+(i+1)+" : ");
            System.out.print(start);
            System.out.print("--");
            System.out.print(end);
            System.out.println();

        }*/
         System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
