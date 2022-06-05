package com.yoj.collections;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipInputStream;

public class L1Solution {
    public static void main(String[] args) {
        FileInputStream redactor = null;
        try {
            redactor = new FileInputStream("D:/test/test.zip.002");
            byte[] buffer = new byte[1024];
            redactor.read(buffer, 0, 1024);
            for (byte b: buffer) {
                System.out.print(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
