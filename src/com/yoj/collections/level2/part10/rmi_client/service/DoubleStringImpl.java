package com.yoj.collections.level2.part10.rmi_client.service;

import java.rmi.RemoteException;

public class DoubleStringImpl implements DoubleString{
    @Override
    public String doubleString(String str) throws RemoteException {
        return str + str;
    }
}
