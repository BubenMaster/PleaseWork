package com.yoj.core.level6;



    public class L6ThreadStatic {
        public static void main(String[] args) {

            TestThread thread = new TestThread();
            thread.start();

        }

        public static class TestThread extends java.lang.Thread {

            static {
                System.out.println("it's a static block inside TestThread");
            }



            @Override
            public void run() {

                System.out.println("it's a run method");
            }

        }
    }

