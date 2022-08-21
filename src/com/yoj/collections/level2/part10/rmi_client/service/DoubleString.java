package com.yoj.collections.level2.part10.rmi_client.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DoubleString extends Remote {
    String doubleString (String str) throws RemoteException;
}
