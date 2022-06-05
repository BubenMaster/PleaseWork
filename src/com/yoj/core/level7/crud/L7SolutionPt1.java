package com.yoj.core.level7.crud;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//-c Журавлев м 15/04/1990 Николаева ж 17/06/1985 Кожухова ж 28/02/1974
//-u 3 Журавлев м 15/04/1990 2 Николаева ж 17/06/1985 0 Кожухова ж 28/02/1974
//-d 3 1
//-d 2 1 0

public class L7SolutionPt1 {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        allPeople.add(Person.createFemale("Синопсина Екатерина", new Date())); //сегодня родился    id=2
        allPeople.add(Person.createMale("Коновалов Савелий", new Date())); //сегодня родился    id=3
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        int id;


        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 0; i < (args.length - 1) / 3; i++) {
                        int n = 3 * i;
                        if (decodeToUTF8(args[n + 2]).equalsIgnoreCase("ж")) {
                            allPeople.add(Person.createFemale(decodeToUTF8(args[n + 1]), prepareDate(args[n + 3])));
                            System.out.println(allPeople.size() - 1);
                            //System.out.println(allPeople.get(2).toString());
                        } else if (decodeToUTF8(args[n + 2]).equalsIgnoreCase("м")) {
                            allPeople.add(Person.createMale(decodeToUTF8(args[n + 1]), prepareDate(args[n + 3])));
                            System.out.println(allPeople.size() - 1);
                        }
                    }
                }
                break;
            case "-r":
                id = Integer.parseInt(args[1]);
                System.out.println(allPeople.get(id).toString());
                break;
            case "-u":
                for (int i = 0; i < (args.length -1) /4; i++) {
                    int n = 4 * i;
                    id = Integer.parseInt(args[n + 1]);
                    if (decodeToUTF8(args[n + 3]).equalsIgnoreCase("ж")) {
                        allPeople.set(id, Person.createFemale(decodeToUTF8(args[n + 2]), prepareDate(args[n + 4])));
                        //System.out.println(allPeople.size() - 1);
                        //System.out.println(allPeople.get(id).toString());
                    }
                    else if (decodeToUTF8(args[n + 3]).equalsIgnoreCase("м")) {
                        allPeople.set(id, Person.createMale(decodeToUTF8(args[n + 2]), prepareDate(args[n + 4])));
                        //System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 0; i < args.length - 1; i++) {
                        id = Integer.parseInt(args[i + 1]);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setName(null);
                        allPeople.get(id).setBirthDate(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 0; i < args.length - 1; i++) {
                        id = Integer.parseInt(args[i + 1]);
                        System.out.println(allPeople.get(id).toString());
                    }
                }
                break;
            default: ;
        }

        for (Person pers: allPeople) {
            System.out.println(pers.toString());
        }


    }

    private static Date prepareDate(String dateLine) {
        Date date = new Date();
        StringBuilder builder = new StringBuilder(dateLine);
        int indexD = builder.indexOf("/");
        date.setDate(Integer.parseInt(builder.substring(0, indexD)));
        int indexM = builder.delete(0, indexD+1).indexOf("/");
        date.setMonth(Integer.parseInt(builder.substring(0, indexM)) -1);
        date.setYear(Integer.parseInt(builder.delete(0, indexM+1).toString()) - 1900);
        return date;
    }

    private static String decodeToUTF8 (String stringMS1251) {
        byte[] bytes;
        String stringUTF8;
        try {
            bytes = stringMS1251.getBytes("windows-1251");
            stringUTF8 = new String(bytes, "UTF-8");
            return  stringUTF8;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Failed decode!");;
        }
        return null;
    }


}
