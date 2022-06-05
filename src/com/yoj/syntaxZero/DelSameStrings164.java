package com.yoj.syntaxZero;


import java.util.Scanner;

public class DelSameStrings164 {
    public static String[] strings;

    public static void main(String[] args) {
        Scanner ska = new Scanner(System.in);
        boolean bool = false;
        strings = new String[6];
         for (int i = 0; i < strings.length; i++) {
            strings[i] = ska.nextLine() ;
        }
        for (int i = 0; i < strings.length - 1; i++) {
            if (strings[i] == null) continue;
            for (int j = i + 1; j < strings.length; j++) {
                if  (!(strings[j] == null)) {
                    if ( strings[i].equals(strings[j])) {
                        strings[j] = null;
                        bool = true;
                    }
                }
            }
            if ( bool == true) strings[i] = null;
            bool = false;
        }

        //напишите тут ваш код
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i] + ", ");
        }
    }
}
