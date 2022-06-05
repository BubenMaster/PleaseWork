package com.yoj.collections.level1;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class FileDownloader {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://www.best-poems.net/files/imagecache/poet/category_pictures/Thomas_Moore.jpg", Paths.get("D:\\test\\testTarget"));

        /*for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }*/
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        String fileName = Paths.get(url.getPath()).getFileName().toString();


        InputStream reader = url.openStream();
        //byte[] buffer = reader.readAllBytes();
        Path tempPath = Files.createTempFile(null, null);
        //System.out.println("Temporary File: " + tempPath.toString());
        //Files.write(tempPath,buffer);
        //Path copyPath = tempPath.getParent().resolve(fileName);
        Files.copy(reader, tempPath, StandardCopyOption.REPLACE_EXISTING);
        //System.out.println("Moved to: " + downloadDirectory.resolve(fileName));
        Path destination = downloadDirectory.resolve(fileName);
        Files.move(tempPath, destination, StandardCopyOption.REPLACE_EXISTING);
        return destination;
        // implement this method
    }
}
