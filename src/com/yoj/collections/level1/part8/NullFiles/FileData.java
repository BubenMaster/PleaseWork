package com.yoj.collections.level1.part8.NullFiles;

public interface FileData {
    boolean isNull();

    boolean isHidden();

    boolean isExecutable();

    boolean isDirectory();

    boolean isWritable();
}