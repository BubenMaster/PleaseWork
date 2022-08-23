package com.yoj.collections.level2.part10.service_locator;

import com.yoj.collections.level2.part10.service_locator.service.Service;

public class ServiceLocatorTest {

    public static void main(String[] args) {
        Service service = ServiceLocator.getService("EJBService");
        service.execute();
        System.out.println();
        service = ServiceLocator.getService("JMSService");
        service.execute();
        System.out.println();
        service = ServiceLocator.getService("EJBService");
        service.execute();
        System.out.println();
        service = ServiceLocator.getService("JMSService");
        service.execute();

    }
}
