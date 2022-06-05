package com.yoj.collections.level1.part15;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/*
Десериализация
*/

public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {

        try {
            return (A) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {


        List<String> list = new CustomTree();

        for (int i = 1; i < 17; i++) {
            list.add(String.valueOf(i));
        }

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 1; i < 16; i++) {
                    list.add(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


         //list.add("6");


        /*System.out.println("removed 7: " + ((CustomTree) list).remove("7"));
        System.out.println("removed 8: " + ((CustomTree) list).remove("8"));
        System.out.println("removed 9: " + ((CustomTree) list).remove("9"));
        System.out.println("removed 10: " + ((CustomTree) list).remove("10"));
        System.out.println("removed 5: " + ((CustomTree) list).remove("5"));
        System.out.println("removed 6: " + ((CustomTree) list).remove("6"));*/

       // list.add("18");


        System.out.println("The list size is " + list.size());
        System.out.println("The expected parent is 3. The actual parent is " + ((CustomTree) list).getParent("8"));
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("20"));

        System.out.println("Parent of 10: " + ((CustomTree) list).getParent("10"));
        System.out.println("Parent of 5: " + ((CustomTree) list).getParent("5"));
        System.out.println("Parent of 16: " + ((CustomTree) list).getParent("16"));
        System.out.println("Parent of 13: " + ((CustomTree) list).getParent("13"));
        System.out.println("Parent of 18: " + ((CustomTree) list).getParent("18"));

        System.out.println("removed 16: " + ((CustomTree) list).remove("16"));
        System.out.println("size: " + (list).size());
    }




}
