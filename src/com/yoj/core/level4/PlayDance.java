package com.yoj.core.level4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PlayDance {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Person person = null;
        String key;
        while (!(key = reader.readLine()).equals("exit")) {
            if ("player".equals(key)) {
                person = new Player();
            } else if ("dancer".equals(key)) {
                person = new Dancer();
            }
                else person = new Person() {
                    @Override
                    public void doNothing() {
                        Person.super.doNothing();
                    }
                };
            haveFun(person);
        }
    }

    public static void haveFun(Person person) {

        if (person instanceof Player) ((Player) person).play();
        else if (person instanceof Dancer) ((Dancer) person).dance();
        else person.doNothing();
        //напишите тут ваш код
    }

    interface Person {
        default void doNothing() {
            System.out.println("doing nothing");
        }
    }

    static class Player implements Person {
        void play() {
            System.out.println("playing");
        }
    }

    static class Dancer implements Person {
        void dance() {
            System.out.println("dancing");
        }
    }
}
