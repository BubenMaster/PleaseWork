package com.yoj.collections.level1.zipFile;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//"D:/test/test.zip"
//"D:/test/result.mp3"
public class Unzip {

    public static void main(String[] args) {
        try {
            System.out.println(String.valueOf(Files.deleteIfExists(Paths.get(args[0]))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File resultPath = new File(args[0]);
        try {

            FileOutputStream fileWriter = new FileOutputStream(resultPath);

            StringBuilder redactor = new StringBuilder(args[1]);
            redactor.delete(redactor.lastIndexOf("."),redactor.length());
            FileInputStream zipReader = new FileInputStream(redactor.toString());
            //ZipInputStream zipInput = new ZipInputStream(zipReader);
            byte[] buffer = new byte[1000] ;

            boolean skipFirst = true;
            List<String> partNames = new ArrayList<>();
            pointer: for (String arg: args) {
                    if (skipFirst) {
                        skipFirst = false;
                        continue pointer;
                    }
                StringBuilder nameGetter = new StringBuilder(arg);
                nameGetter.delete(0,nameGetter.lastIndexOf(".") + 1);
                partNames.add(nameGetter.toString());
            }
            Collections.sort(partNames);






            List<String> zipNames = getZipNames(redactor.toString());
            List<Boolean> compResult = new ArrayList<>();
            zipNames.forEach(System.out::println);
            for (int i = 0; i < zipNames.size(); i++) {
                compResult.add(zipNames.get(i).equalsIgnoreCase(partNames.get(i)));

            }
            compResult.forEach(System.out::println);
            //partNames.forEach(System.out::println);



            /*ZipEntry entry = zipInput.getNextEntry();

            while (entry != null) {
                while (zipInput.available() != 0) {
                    int read = zipInput.read(buffer, 0,1000);
                    System.out.printf("read part %s, size %d bytes, with chunk: %d bytes \n", entry.getName(), entry.getSize(), read);
                    if (read == -1) break;
                    fileWriter.write(buffer,0, read);
                }
                fileWriter.flush();
                entry = zipInput.getNextEntry();
            }
            zipInput.close();*/

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ye bitch");;
        } catch (IOException e) {
            System.out.println("IOException shiet");;
        }
    }

    private static FileInputStream getFileInputStream (FileInputStream zipToGet, String part) {
        ZipInputStream zis = new ZipInputStream(zipToGet);
        FileInputStream result = null;
        byte[] buffer = new byte[1024];
        Path temPath = null;
        FileOutputStream fileWriter = null;
        try {
            temPath = Files.createTempFile(null, null);
            fileWriter = new FileOutputStream(temPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
                if (entry.getName().equalsIgnoreCase(part)) {
                    while (zis.available() != 0) {
                        int read = zis.read(buffer, 0,1024);
                        // System.out.printf("read part %s, size %d bytes, with chunk: %d bytes \n", entry.getName(), entry.getSize(), read);
                        if (read == -1) break;
                        fileWriter.write(buffer,0, read);
                    }
                }
            }
            System.out.println(temPath.toFile().getName() + "  " + Files.size(temPath));
           result = new FileInputStream(temPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<String> getZipNames(String fileName) {
        List<String> result = new ArrayList<>();
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileName))) {
            for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
                result.add(entry.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
