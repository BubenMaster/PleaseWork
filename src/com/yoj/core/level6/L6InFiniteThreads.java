package com.yoj.core.level6;

public class L6InFiniteThreads {
    static int count = 15;
    static volatile int createdThreadCount;

    public static void main(String[] args) {
        System.out.println(new GenerateThread());
    }

    public static class GenerateThread extends Thread {
        public GenerateThread() {
            super(String.valueOf(++createdThreadCount));
            start();
        }

        public void run() {
            if (createdThreadCount < count) {
                System.out.println(new GenerateThread());
            }
        }

        @Override
        public String toString() {
            return getName() + " created";
        }
    }
} /*{
    static int count = 15;
    static volatile int createdThreadCount ;

    public static void main(String[] args) {
        //System.out.println(new GenerateThread());
        new GenerateThread();
    }

    public static class GenerateThread extends Thread {

        public GenerateThread() {
            super(String.valueOf(++createdThreadCount));
           start();
        }

        public GenerateThread(String name) {
            this.setName(name);
            new GenerateThread();
        }

        @Override
        public String toString() {
            return currentThread().getName() + "created";
        }

        @Override
        public void run() {
            if (createdThreadCount <= count) {
                System.out.println(currentThread());
                new GenerateThread();

            }
        }
    }
}*/

