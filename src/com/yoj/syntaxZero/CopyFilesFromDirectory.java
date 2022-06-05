package com.yoj.syntaxZero;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CopyFilesFromDirectory {
    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";

        public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Path sourceDirectory = Path.of(scanner.nextLine());
        Path targetDirectory = Path.of("D:\\test\\testTarget");

            try (DirectoryStream<Path> dirList = Files.newDirectoryStream(sourceDirectory)) {
                for (Path singleDir:
                     dirList) {
                    if (Files.isRegularFile(singleDir)) {
                        StringBuilder dest = new StringBuilder(targetDirectory.toString());
                        dest.append("\\");
                        dest.append(singleDir.getFileName().toString());
                        Path destinyNew = Path.of(dest.toString()); // targetDirectory.resolve(singleDir.getFileName());
                        //System.out.println(targetDirectory);
                        if (!Files.exists(destinyNew) && Files.isRegularFile(singleDir)) {
                            Files.move(singleDir, destinyNew);
                            System.out.println(singleDir + " скопировано в: ");
                            System.out.println(destinyNew);
                        }
                    }
                    if (Files.isDirectory(singleDir))
                        System.out.println(singleDir + THIS_IS_DIR);
                }
            }

                 //напишите тут ваш код

    }
}
