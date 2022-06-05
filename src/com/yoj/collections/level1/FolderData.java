package com.yoj.collections.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FolderData {
    //D:\test
    //G:\JavaWorkspace\Inellij Workspace\Please Work\src\com\yoj\TestFiles

    public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String folderName = reader.readLine();
    Path folderPath = CheckToPath(folderName);
    if (folderPath == null) System.exit(4011);
    Set<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
    Counter counter = new Counter();

    Files.walkFileTree(folderPath, options, Integer.MAX_VALUE, new SimpleFileVisitor<Path>(){

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            counter.countSize(Files.size(file));
            counter.countFiles();
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (!dir.equals(folderPath)) counter.countFolders();
            return super.preVisitDirectory(dir, attrs);
        }
    });
        System.out.println("Параметры папки " + folderPath.toString() + ":");
        System.out.printf("Всего папок - %s\n" +
                "Всего файлов - %s\n" +
                "Общий размер - %s байт", counter.getFolders().toString(), counter.getFiles().toString(), counter.getFolderSize().toString());

    }

    private static Path CheckToPath(String folderName) {
        Path path = Paths.get(folderName);
        //System.out.println(path);
        if (path.toFile().exists() && path.toFile().isDirectory()) return path;
        else {
            System.out.println(path.toString() + " - не папка");
            return null;
            }
    }

    public static class Counter {
        private AtomicInteger files, folders;
        private AtomicLong folderSize;

        public Counter() {
            files = new AtomicInteger(0);
            folders = new AtomicInteger(0);
            folderSize = new AtomicLong(0L);
        }

        public void countSize(long size) {
            this.folderSize.addAndGet(size);
        }

        public void countFiles() {
            this.files.incrementAndGet();
        }

        public void countFolders() {
            this.folders.incrementAndGet();
        }

        public AtomicInteger getFiles() {
            return this.files;
        }

        public AtomicLong getFolderSize() {
            return this.folderSize;
        }

        public AtomicInteger getFolders() {
            return this.folders;
        }
    }


}
