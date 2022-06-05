package com.yoj.collections.level1.searchFileVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/*
Продвинутый поиск файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        SearchFileVisitor searchFileVisitor = new SearchFileVisitor();

        searchFileVisitor.setPartOfName("amigo");
        searchFileVisitor.setPartOfContent("the");//"programmer");
        searchFileVisitor.setMinSize(500);
        searchFileVisitor.setMaxSize(1300);
        //D:/SecretFolder
        Files.walkFileTree(Paths.get("G:\\JavaWorkspace\\Inellij Workspace\\Please Work\\src\\com\\yoj\\TestFiles"), searchFileVisitor);

        List<Path> foundFiles = searchFileVisitor.getFoundFiles();
        for (Path file : foundFiles) {
            System.out.println(file.toString());
        }
    }

}
