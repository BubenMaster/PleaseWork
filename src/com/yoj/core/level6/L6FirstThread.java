package com.yoj.core.level6;


public class L6FirstThread {
    public static void main(String[] args) {
        TestThread thread = new TestThread("first");
        Thread process1 = new Thread(thread, "SayMyName");
        Thread process2 = new Thread(thread, "WrongName");
        process1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        process2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static class TestThread  implements  Runnable{
        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            System.out.print("running: ");
            System.out.println(Thread.currentThread().getName());
        }
    }
}

