package com.yoj.core.level123;
import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.zip.CheckedInputStream;

public class fileReader {

    // D:\test\testTarget\testicle.txt

    public static void main(String[] args) throws IOException {
        BufferedReader pathString = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try {
            string = pathString.readLine();
        }
        catch (IOException e) {
        }
        pathString.close();

        System.out.println("Вбит путь: " + string);

         InputStream stream = new FileInputStream(string);
            while (stream.available() > 0) {
                System.out.print( (char) stream.read());
            }
        stream.close();
            // напишите тут ваш код
    }

}
