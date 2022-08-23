package com.yoj.collections.level3.part5.class_convertation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ClassConversionTest {

    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, one);
        String jsonString = writer.toString();

/**
  <p>Simply change class names in json string.
 */

        System.out.println(jsonString);
        String originClassName = "\"" + one.getClass().getSimpleName().toLowerCase() + "\"";
        System.out.println(originClassName);
        String resultClassName = "\"" + resultClassObject.getSimpleName().toLowerCase() + "\"";
        System.out.println(resultClassName);
        jsonString = jsonString.replaceFirst(originClassName,resultClassName);
        System.out.println(jsonString);

        StringReader reader = new StringReader(jsonString);
        return mapper.readValue(reader, resultClassObject);
    }

    @JsonAutoDetect
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = First.class, name = "first")
    })
    public static class First {
        public int i;
        public String name;

        public First() {
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonAutoDetect
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;

        public Second() {
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
