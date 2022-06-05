package com.yoj.collections.level2.part2;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Запись в существующий файл
*/

public class RewriteExistingFile {
    public static void main(String... args) {
        if (Files.exists(Paths.get(args[0]))) {
            try (RandomAccessFile fileAdder = new RandomAccessFile(args[0], "rw")) {
                if (fileAdder.length() < Long.parseLong(args[1])) {
                    fileAdder.seek(fileAdder.length());
                    fileAdder.write(args[2].getBytes("windows-1251"));

                } else {
                    fileAdder.seek(Long.parseLong(args[1]));
                    fileAdder.write(args[2].getBytes("windows-1251"));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

