package com.yoj.core.leve10.part7.FamilyRnW;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Externalizable Person
*/

public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public Person() {

        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName =(String) in.readObject();
            lastName =(String) in.readObject();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            age = in.readInt();
            children = (List) in.readObject();
        }
    }

    public static void main(String[] args) {
        Person me = new Person("myName", "mySecondName", 30);
        Person bro = new Person("broName", "broSecondName", 45);
        Person broKid = new Person("broKidName", "broKidSecondName", 14);
        Person broKidSis = new Person("broKidSisName", "broKidSisSecondName", 14);
        Person ma = new Person("maName", "maSecondName", 74);
        Person pa = new Person("paName", "paSecondName", 74);

        bro.setChildren(List.of(broKid, broKidSis));
        bro.setFather(pa);
        bro.setMother(ma);

        Person personClone = null;

        File tmpFile = null;
        try {
            tmpFile = File.createTempFile("tmp", null, new File("G:\\JavaWorkspace\\Inellij Workspace\\Please Work\\src\\com\\yoj\\core\\leve10\\part7\\FamilyRnW"));
            ObjectOutputStream ooutput = new ObjectOutputStream(new FileOutputStream(tmpFile));
            ObjectInputStream oinput = new ObjectInputStream(new FileInputStream(tmpFile));
            ooutput.writeObject(bro);
            Object object = oinput.readObject();
            personClone = (Person) object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(personClone.firstName + " " + personClone.lastName);
        personClone.children.forEach(e -> System.out.print(e.firstName + "  "));
        System.out.println();
        System.out.println(personClone.father.firstName + " " + personClone.mother.firstName);

    }
}
