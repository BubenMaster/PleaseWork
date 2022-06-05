package com.yoj.syntaxZero;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class TemporaryFile {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        URL url = new URL(line);
        InputStream stream = url.openStream();
        byte[] byteBuffer = stream.readAllBytes();
        Path tempFilePath = Files.createTempFile(Path.of("D:\\test\\testTarget"), null,null);
        System.out.println(tempFilePath);
        Files.write(tempFilePath, byteBuffer);

    }
}
