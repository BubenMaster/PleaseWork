package com.yoj.collections.level3.part5.serialization2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.io.StringWriter;

public class Serialization2Test {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 3;

        StringWriter writer = new StringWriter();
        convertToJSON(writer, cat);
        System.out.println(writer.toString());
//        {"wildAnimal":"Murka","over":3}
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    @JsonAutoDetect
    public static class Cat {

        @JsonProperty("wildAnimal")
        public String name;

        @JsonIgnore
        public int age;

        @JsonProperty("over")
        public int weight;

        Cat() {
        }
    }
}
