package com.yoj.core.level123;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenNumbersFromFile {

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

        FileInputStream stream = new FileInputStream(string);
        BufferedReader reader = new BufferedReader( new InputStreamReader(stream));

        ArrayList<Integer> numbers = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            if (!line.equals("exit")) numbers.add(Integer.parseInt(line));
        }
        numbers.stream().filter(x -> x % 2 == 0).sorted(Comparator.comparingInt(x -> x)).forEach(System.out::println);
        stream.close();
        reader.close();
        // напишите тут ваш код
    }
}
