package com.example.dubbo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Louyp
 * @version 1.0
 * @data 2020/09/23 14:18
 */
public class TestThread {

    public static void main(String[] args) {
        final List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6");
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                synchronized (strings){
                    for (String string : strings) {
                        System.out.println(string);
                    }
                }
            }
        });
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                synchronized (strings){
                    for (String string : strings) {
                        System.out.println(string);
                    }
                }
            }
        });
    }
}
