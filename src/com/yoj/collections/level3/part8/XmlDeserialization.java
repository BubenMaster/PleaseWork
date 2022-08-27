package com.yoj.collections.level3.part8;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.io.IOException;
import java.io.StringReader;

public class XmlDeserialization {
    public static void main(String[] args) throws IOException, JAXBException {
        String xmlData = "<cat><name>Murka</name><age>5</age><weight>4</weight></cat>";

//        Unmarshaller unmarshaller = JAXBContext.newInstance(Cat.class).createUnmarshaller();
//        Cat cat = (Cat) unmarshaller.unmarshal(new StringReader(xmlData));

        Cat cat = convertFromXmlToNormal(xmlData, Cat.class);

        System.out.println(cat);
    }

    public static <T> T convertFromXmlToNormal(String xmlData, Class<T> clazz) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xmlData));
    }

    @XmlType(name = "cat")
    @XmlRootElement
    public static class Cat {
        public String name;
        public int age;
        public int weight;

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }
}
