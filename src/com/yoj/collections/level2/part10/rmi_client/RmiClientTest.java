package com.yoj.collections.level2.part10.rmi_client;

import com.yoj.collections.level2.part10.rmi_client.service.DoubleString;
import com.yoj.collections.level2.part10.rmi_client.service.DoubleStringImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiClientTest {
    public static String UNIC_BINDING_NAME = "double.string";
    private static String  TestingWord = "Jo";
    public static Registry registry;
    public static int port = 2099;

    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run(){
            DoubleString service;
            try {

                service = (DoubleString) registry.lookup(UNIC_BINDING_NAME);

                System.out.println(service.doubleString(TestingWord));


            } catch (RemoteException | NotBoundException e) {
                throw new RuntimeException(e);
            }

        }
    });


    public static void main(String[] args) {
        Remote stub;

        final DoubleStringImpl service =new DoubleStringImpl();
        try {
            registry = LocateRegistry.createRegistry(port);
            stub = UnicastRemoteObject.exportObject(service, 0);
            registry.bind(UNIC_BINDING_NAME,stub);

        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
        CLIENT_THREAD.start();

    }


}
