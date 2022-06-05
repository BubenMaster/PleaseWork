package com.yoj.core.level123;

import java.util.List;
import java.util.stream.Stream;

public class Interfacing {

    public static void main(String[] args) throws Exception {
        SimpleObject<String> stringObject = new StringObject();
    }

    int name = 1;
    String nameStr = String.valueOf(name);

    interface SimpleObject<T> {
        SimpleObject<T> getInstance();
    }

    public static class StringObject<T> implements SimpleObject<T> {

        public StringObject() {

        }

        public StringObject<T> getInstance() {
            return this;
        }

    }


}
