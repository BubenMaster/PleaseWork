package com.yoj.core.level6;

public class L6Politota {
    public static int totalSpeechCount = 200;
    public static int utterancesPerSpeech = 1000000;

    public static void main(String[] args) throws InterruptedException {
        Politician ivanov = new Politician("Иванов");
        Politician petrov = new Politician("Петров");
        Politician sidorov = new Politician("Сидоров");


        //ivanov.join();

        while (ivanov.getSpeechCount() + petrov.getSpeechCount() + sidorov.getSpeechCount() < totalSpeechCount) {
            /*if (ivanov.getSpeechCount() + petrov.getSpeechCount() + sidorov.getSpeechCount() >= totalSpeechCount) {
                petrov.interrupt();
                sidorov.interrupt();
            }*/

        }

        //petrov.interrupt();
        //sidorov.interrupt();

        System.out.println(ivanov);
        System.out.println(petrov);
        System.out.println(sidorov);
    }

    public static class Politician extends Thread {
        private volatile int utteranceCount;

        public Politician(String name) {
            super(name);
            start();
            if (this.getName().equals("Иванов")) {
                try {
                    this.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            while (utteranceCount < totalSpeechCount * utterancesPerSpeech) {
                this.utteranceCount++;
            }
        }

        public int getSpeechCount() {

            return utteranceCount / utterancesPerSpeech;
        }

        @Override
        public String toString() {
            return String.format("%s сказал речь %d раз", getName(), getSpeechCount());
        }
    }
}

