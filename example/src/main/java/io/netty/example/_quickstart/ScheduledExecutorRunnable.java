package io.netty.example._quickstart;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorRunnable {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor ses = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Running task2...");
            }
        };

        task1();

        //run this task after 5 seconds, nonblock for task3
        ses.schedule(task2, 5, TimeUnit.SECONDS);

        task3();

        ses.shutdown();

    }

    public static void task1() {
        System.out.println("Running task1...");
    }

    public static void task3() {
        System.out.println("Running task3...");
    }

}

