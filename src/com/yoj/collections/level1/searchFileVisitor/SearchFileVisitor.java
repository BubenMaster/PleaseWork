package com.yoj.collections.level1.searchFileVisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    List<Path> foundFiles = new ArrayList<>();
    String partOfName, partOfContent;
    int minSize;
    int maxSize;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        int checkSum = 0;

        if (this.partOfName != null) {
            if (file.getFileName().toString().toLowerCase().contains(this.partOfName)) {
              checkSum++;//this.foundFiles.add(filePath);
            }
        }
        else {
            checkSum ++;}

        if (file.toFile().isFile() && this.partOfContent != null && !partOfContent.isEmpty()) {
            for (String line: Files.readAllLines(file)){
                if (line.toLowerCase().contains(this.partOfContent)) {
                    checkSum++;//this.foundFiles.add(filePath);
                    break;
                }
            }
        }
        else {
            checkSum ++;}

        if (this.maxSize > this.minSize || (this.minSize ==0 && this.maxSize !=0) || (this.minSize !=0 && this.maxSize ==0)) {
            if (file.toFile().isFile() && this.maxSize > 0) {
                if (Files.size(file) < this.maxSize) {
                    checkSum++;
                }
            } else {
                checkSum++;
            }

            if (file.toFile().isFile() && this.minSize > 0) {
                if (Files.size(file) > this.minSize) {
                    checkSum++;
                }
            }
            else {
                checkSum++;
            }
        }
        else {checkSum += 2;}

        if (checkSum == 4) {
            this.foundFiles.add(file);
        }

        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String amigo) {
        this.partOfName = amigo;
    }

    public void setPartOfContent(String programmer) {
        this.partOfContent = programmer;
    }

    public void setMinSize(int i) {
        this.minSize = i;
    }

    public void setMaxSize(int i) {
        this.maxSize = i;
    }

    public List<Path> getFoundFiles() {
        return this.foundFiles;
    }
}
