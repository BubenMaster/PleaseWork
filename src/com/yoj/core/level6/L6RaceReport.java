package com.yoj.core.level6;

public class L6RaceReport {
    public static volatile int numSeconds = 4;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(3500);
        clock.interrupt();
        //add your code here - добавь код тут
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            int seconds = numSeconds;
            int counter = 0;
            try {
                while (seconds != 0) {
                    if (counter == 7) {
                        this.interrupt();
                    }
                    else if (counter % 2 == 0){
                        System.out.printf("%d ", seconds);
                    }
                    else if (counter % 2 == 1){
                        seconds--;
                    }
                    counter++;
                    Thread.sleep(500);
                    if (seconds == 0) System.out.println("Марш!");
                }
            }
            catch (InterruptedException e) {
                System.out.println("Прервано!");
            }
            //add your code here - добавь код тут
        }
    }
}
