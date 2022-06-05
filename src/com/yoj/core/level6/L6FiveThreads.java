package com.yoj.core.level6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class L6FiveThreads {


        public static List<Thread> threads = new ArrayList<>(5);

        public static void main(String[] args) {
            threads.add(new InterruptedThread());
            threads.add(new HoorayThread());
            threads.add(new MessageThread());
            threads.add(new ConsoleThread());
            threads.forEach(Thread::start);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            threads.get(0).interrupt();
            threads.get(1).interrupt();
            ((MessageThread) threads.get(2)).showWarning();


            //N
            // System.out.println("Wait for this...");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //System.out.println(threads.get(0).getState());
        }

    public interface Message {
        void showWarning();
    }

    public static class InfiniteThread extends Thread {

        public InfiniteThread() {

        }

        @Override
        public void run() {
            while (true) {

            }
        }
    }
     public static class InterruptedThread extends  Thread {

        public InterruptedThread() {
        }

         @Override
         public void run() {
             boolean interrupted = false;
             try {
                 while (!interrupted) {
                     interrupted = Thread.currentThread().isInterrupted();
                     Thread.sleep(1);
                 }
             }
             catch (InterruptedException e) {
                 System.out.println("InterruptedExceptionInterrupt");
             }
         }
     }

     public static class HoorayThread extends Thread {

         @Override
         public void run() {
             boolean interrupted = false;
             try {
                 while (!interrupted) {
                     interrupted = Thread.currentThread().isInterrupted();
                     Thread.sleep(500);
                     System.out.println("Ура");
                 }
             }
             catch (InterruptedException e) {
                 System.out.println("InterruptedExceptionHoor");
             }
         }
     }


    public static class MessageThread extends Thread implements Message {

        @Override
        public void run() {
            boolean interrupted = false;
            while (!interrupted) {
                interrupted = Thread.currentThread().isInterrupted();
            }
        }

        @Override
        public void showWarning() {
            this.interrupt();
        }
    }

    public static class ConsoleThread extends Thread {

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            int sum = 0;

            try {
                while (!line.toUpperCase().equals("N")) {
                    line = reader.readLine();
                    if (isDigit(line)) sum += Integer.parseInt(line);
                }
                System.out.println("Summary = " + sum);
            }
            catch (IOException e) {
                System.out.println("IOException");
            }
        }

        private static boolean isDigit(String s) throws NumberFormatException {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

}
