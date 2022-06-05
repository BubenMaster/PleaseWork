package com.yoj.collections.level1.part8.NullFiles;

public class NullFileData implements FileData {
    private Exception exception;

    public NullFileData(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isWritable() {
        return false;
    }
}
