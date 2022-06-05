package com.yoj.core.level6;

import java.util.Arrays;

public class L6StackTrace {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SpecialThread());
        thread.start();

        System.out.println("*****************");

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println(element);
        }
    }

    public static class SpecialThread implements Runnable {


        @Override
        public void run() {
           Thread thread = new Thread(this);

            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            for (StackTraceElement elem : trace) {
                System.out.println(elem);
            }
        }
    }
}