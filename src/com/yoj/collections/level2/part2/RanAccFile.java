package com.yoj.collections.level2.part2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;



public class RanAccFile {

    static
        String no = "false",
               yes = "true";

    public static void main(String... args) {
        int wordLength = args[2].length();
        Long startLong = Long.parseLong(args[1]);
        byte[] word = new byte[wordLength];
        boolean recordingYes = false;

        if (Files.exists(Paths.get(args[0]))) {
            try (RandomAccessFile fileAdder = new RandomAccessFile(args[0], "rw")) {
                Long length = fileAdder.length();
                if (length < startLong) {
                    fileAdder.seek(fileAdder.length());
                    fileAdder.write(no.getBytes("windows-1251"));
                } else {
                    fileAdder.seek(startLong);
                    if (fileAdder.read(word, 0, wordLength) < 0) {
                        System.out.println(word);
                        fileAdder.seek(fileAdder.length());
                        fileAdder.write(no.getBytes("windows-1251"));
                    }
                        else {
                            String wordString = new String(word);
                            if (wordString.equalsIgnoreCase(args[2])) {
                                fileAdder.seek(fileAdder.length());
                                fileAdder.write(yes.getBytes("windows-1251"));
                            } else {
                                System.out.println(wordString + "не совпало");
                                fileAdder.seek(fileAdder.length());
                                fileAdder.write(no.getBytes("windows-1251"));
                            }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
