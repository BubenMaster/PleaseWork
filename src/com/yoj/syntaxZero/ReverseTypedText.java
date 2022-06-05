package com.yoj.syntaxZero;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class ReverseTypedText {
    public static ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public static PrintStream stream = new PrintStream(outputStream);

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        printSomething(scanner.nextLine());
        String arr = reverseString(outputStream.toString());
        System.out.println("arr:" + arr);
        byte[] b = arr.getBytes();
        System.out.println("Bytes: " + Arrays.toString(b));
        outputStream.reset();
        outputStream.writeBytes(b);
        String result = outputStream.toString();
        System.out.println(result);

        //напишите тут ваш код
    }

    public static void printSomething(String str) {
        stream.print(str);
    }
     public static void reverseArray(char[] in) {
             char[] buffer = new char[in.length];
             for (int i = 0; i < in.length; i+=1) {
                 buffer[i] = in[in.length - i - 1];
             }

             for (int i = 0; i < in.length; i++)
                 in[i] = buffer[i];
             }
     public static String reverseString(String str) {
        char[] chars = new char[str.length()];
        str.getChars(0,str.length(), chars, 0);
         System.out.println("до: " + Arrays.toString(chars));
        reverseArray(chars);
         System.out.println("после: " + Arrays.toString(chars));
        return String.valueOf(chars);

     }
     }






