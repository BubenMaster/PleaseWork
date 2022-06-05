package com.yoj.collections.level1.part8.NullFiles;

import java.io.Serializable;

public class ConcreteFileData implements FileData, Serializable {

    private boolean hidden;
    private boolean executable;
    private boolean directory;
    private boolean writable;

    public ConcreteFileData(boolean hidden, boolean executable, boolean directory, boolean writable) {
        this.hidden = hidden;
        this.executable = executable;
        this.directory = directory;
        this.writable = writable;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public boolean isExecutable() {
        return executable;
    }

    @Override
    public boolean isDirectory() {
        return directory;
    }

    @Override
    public boolean isWritable() {
        return writable;
    }
}
