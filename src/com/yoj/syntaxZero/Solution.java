package com.yoj.syntaxZero;

import java.util.Scanner;

public class Solution {

    public static int result;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String stroka = "";
        int number = 0, i = 0;

        do {
            if (scan.hasNextInt()) number = scan.nextInt();
            else stroka = scan.next();
            i++;
        }
        while (i < 2);

        if ((number > 0) && ( number < 5)) {
            i = number;
            do {
                System.out.println(stroka);
                i--;
            }
            while (i > 0);
        }
        else System.out.println(stroka);
        }
    }

