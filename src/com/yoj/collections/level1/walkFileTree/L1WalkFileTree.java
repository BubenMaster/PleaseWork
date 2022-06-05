package com.yoj.collections.level1.walkFileTree;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class L1WalkFileTree {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        Files.walkFileTree(Paths.get(root), EnumSet.of(FileVisitOption.FOLLOW_LINKS),Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        result.add(file.toAbsolutePath().toString());
                        return super.visitFile(file, attrs);
                    }
                });
        return result;
    }

    public static void main(String[] args) {
        String path = "D:\\test\\testTarget";
        try {
            getFileTree(path).stream().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
