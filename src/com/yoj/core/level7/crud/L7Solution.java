package com.yoj.core.level7.crud;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import sun.nio.cs.*;

public class L7Solution {

    /*-c name sex bd
    -r id
    -u id name sex bd
    -d id*/

    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1

    }
    //Евгения ж 19/06/1986
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        int id;


        //System.out.println(format.format(prepareDate("25/01/1990")));
        switch (args[0]) {
            case "-c":
                if (decodeToUTF8(args[2]).equalsIgnoreCase("ж")) {
                    allPeople.add(Person.createFemale(decodeToUTF8(args[1]), prepareDate(args[3])));
                    System.out.println(allPeople.size() - 1);
                    //System.out.println(allPeople.get(2).toString());
                }
                else if (decodeToUTF8(args[2]).equalsIgnoreCase("м")) {
                    allPeople.add(Person.createMale(decodeToUTF8(args[1]), prepareDate(args[3])));
                    System.out.println(allPeople.size() - 1);
                }
                break;
            case "-r":
                id = Integer.parseInt(args[1]);
                System.out.println(allPeople.get(id).toString());
                break;
            case "-u":
                id = Integer.parseInt(args[1]);
                if (decodeToUTF8(args[3]).equalsIgnoreCase("ж")) {
                    allPeople.set(id, Person.createFemale(decodeToUTF8(args[2]), prepareDate(args[4])));
                    //System.out.println(allPeople.size() - 1);
                    //System.out.println(allPeople.get(id).toString());
                }
                else if (decodeToUTF8(args[3]).equalsIgnoreCase("м")) {
                    allPeople.set(id, Person.createMale(decodeToUTF8(args[2]), prepareDate(args[4])));
                    //System.out.println(allPeople.size() - 1);
                }
                break;
            case "-d":
                id = Integer.parseInt(args[1]);
                allPeople.get(id).setSex(null);
                allPeople.get(id).setName(null);
                allPeople.get(id).setBirthDate(null);
                break;
            default: ;
        }

        for (Person pers: allPeople) {
            System.out.println(pers.toString());
        }
            //напишите тут ваш код

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
