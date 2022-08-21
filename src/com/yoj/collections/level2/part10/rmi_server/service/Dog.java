package com.yoj.collections.level2.part10.rmi_server.service;

import java.rmi.RemoteException;

public class Dog implements Animal{
    private final String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void speak() throws RemoteException {
        System.out.println("Woof");
    }

    @Override
    public void printName() throws RemoteException {
        System.out.println(this.name);
    }
}
