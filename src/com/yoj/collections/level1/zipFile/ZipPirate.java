package com.yoj.collections.level1.zipFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipPirate {
    //"D:\test\01_Ready_steady_go_feat_asher_d.mp3"
    //"D:\\test\\test.zip"
    public static void main(String[] args) {
        try {
            System.out.printf(String.valueOf(Files.deleteIfExists(Paths.get("D:\\test\\test.zip"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileToZip = new File("D:\\test\\01_Ready_steady_go_feat_asher_d.mp3");
        File zipFile = new File("D:\\test\\test.zip");
        try {
            FileInputStream fileStream = new FileInputStream(fileToZip);
            FileOutputStream zipFileStream = new FileOutputStream(zipFile);
            ZipOutputStream zip = new ZipOutputStream(zipFileStream);

            int counter=0;

            while (fileStream.available() > 0) {
                int bytesToDo = fileStream.available();
                //zip.setLevel(9);
                zip.putNextEntry(new ZipEntry(String.format("%03d",++counter)));
                if (bytesToDo < 1000000) {
                    zip.write(fileStream.readNBytes(bytesToDo));
                }
                else {
                    zip.write(fileStream.readNBytes(1000000));
                }
            }

            zip.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
