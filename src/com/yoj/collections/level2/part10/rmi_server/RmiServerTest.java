package com.yoj.collections.level2.part10.rmi_server;

import com.yoj.collections.level2.part10.rmi_server.service.Animal;
import com.yoj.collections.level2.part10.rmi_server.service.Cat;
import com.yoj.collections.level2.part10.rmi_server.service.Dog;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServerTest {
    public static Registry registry;

    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            Animal service;
            Remote stab;
            try{
                int port = 2099;
                registry = LocateRegistry.createRegistry(port);
                service = new Cat("Boris");
                stab = UnicastRemoteObject.exportObject(service,0);
                registry.bind("Cat.Animal", stab);

                service = new Dog("Bobik");
                stab = UnicastRemoteObject.exportObject(service, 1);
                registry.bind("Dog.Animal", stab);

            } catch (RemoteException | AlreadyBoundException e){
                e.printStackTrace();
            }
        }
    });


    public static void main(String[] args) throws InterruptedException {
        SERVER_THREAD.start();
        Thread.sleep(1000);
        CLIENT_THREAD.start();

    }
}
