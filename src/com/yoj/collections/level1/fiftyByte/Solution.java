package com.yoj.collections.level1.fiftyByte;


import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/*
Проход по дереву файлов
*/

public class Solution {
    public static void main(String[] args) {
    File path = new File(args[0]);
    File resultFileAbsolutePath = new File(args[1]);
    File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        //FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try (FileOutputStream output = new FileOutputStream(resultFileAbsolutePath))  {  // allFilesContent
            lister(path, output);
        }
        catch (IOException e) {
           e.printStackTrace();
        }



    }
    //
    private static void lister(File path, FileOutputStream outStream) {
        for (File fileElem : path.listFiles()) {
            if (fileElem.isDirectory()) {
                lister(fileElem, outStream);
            }
            else {
                System.out.println(fileElem);
                if (new StringBuilder(fileElem.toString()).indexOf(".txt") != -1 && fileElem.length() <=50) {
                    try (FileInputStream input = new FileInputStream(fileElem)) {
                        byte[] buffer = new byte[50];

                        //if (input.available() <= 50) {}
                            outStream.write(buffer, 0, input.read(buffer));
                            outStream.write("\n".getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //
}
