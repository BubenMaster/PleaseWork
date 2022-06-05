package com.yoj.syntaxZero;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class IOtest {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://translate.google.com/?sl=en&tl=ru&text=penumbra&op=translate");
        try {
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            /**OutputStream streamO = connection.getOutputStream();
            PrintStream print = new PrintStream(streamO);
            print.println(new Scanner(System.in).nextLine());
            print.close();
            streamO.close(); */


            InputStream streamI = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(streamI));
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
            reader.close();
            streamI.close();

           // connection.setDoOutput(false);

            //String string = Arrays.toString(bytes);
            //System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
