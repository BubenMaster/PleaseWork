package com.yoj.core.level4.userLoser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;
        List<String> personList = new ArrayList<>();
                personList.add("user");
                personList.add("loser");
                personList.add("coder");
                personList.add("proger");

        try {
            //тут цикл по чтению ключей, пункт 1
            while (personList.contains((key = reader.readLine()).toLowerCase()))

            {
                //создаем объект, пункт 2
                switch (key.toLowerCase()) {
                    case "user": person = new Person.User();
                        break;
                    case "loser": person = new Person.Loser();
                        break;
                    case "coder": person = new Person.Coder();
                        break;
                    case  "proger": person = new Person.Proger();
                        break;
                    default: ;
                };
                doWork(person); //вызываем doWork
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        reader.close();
    }
    public static void doWork(Person person) {
        if (person instanceof Person.User) ((Person.User) person).live();
        else if (person instanceof Person.Loser) ((Person.Loser) person).doNothing();
        else if (person instanceof Person.Coder) ((Person.Coder) person).writeCode();
        else if (person instanceof Person.Proger) ((Person.Proger) person).enjoy();
        // пункт 3
    }
}