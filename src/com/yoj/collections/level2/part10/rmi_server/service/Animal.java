package com.yoj.collections.level2.part10.rmi_server.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Animal extends Remote {


    void speak() throws RemoteException;

    default void printName() throws RemoteException{
    }

}
