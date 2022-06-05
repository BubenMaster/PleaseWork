package com.yoj.syntaxZero;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        Path path = Path.of(line);
        if (!Files.exists(path)) {
            System.exit(1);
        } else if (Files.isRegularFile(path)) {
            System.out.println(path + THIS_IS_FILE);
        } else if (Files.isDirectory(path)) {
            System.out.println(path + THIS_IS_DIR);
        }


    }
}

