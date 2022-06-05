package com.yoj.collections.level1.part8;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Исследуем Path
*/

public class PathDifference {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("D:/test/data/firstDir");
        Path path2 = Paths.get("D:/test/data/secondDir/third");
        Path resultPath = getDiffBetweenTwoPaths(path1, path2);
        System.out.println(resultPath);   //expected output '../secondDir/third' or '..\secondDir\third'
    }

    public static Path getDiffBetweenTwoPaths(Path path1, Path path2) {
        return path2.relativize(path1);
    }
}
