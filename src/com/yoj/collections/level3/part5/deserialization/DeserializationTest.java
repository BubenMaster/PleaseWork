package com.yoj.collections.level3.part5.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class DeserializationTest {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileReader reader = new FileReader(new File(fileName));

        return (T) mapper.readValue(reader,clazz);
    }

    public static void main(String[] args) {

    }
}
