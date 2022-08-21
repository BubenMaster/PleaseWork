package com.yoj.collections.level2.part10.rmi_server.service;

import java.rmi.RemoteException;

public class Cat implements Animal{
    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void speak() throws RemoteException {
        System.out.println("Meow");
    }

    @Override
    public void printName() throws RemoteException {
        System.out.println(name);
    }
}
