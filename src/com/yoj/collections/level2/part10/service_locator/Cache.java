package com.yoj.collections.level2.part10.service_locator;

import com.yoj.collections.level2.part10.service_locator.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private final List<Service> services;

    public Cache() {
        services = new ArrayList<>();
    }

    public Service getService(String serviceName){
        for (Service service: services) {
            if (service.getName().equalsIgnoreCase(serviceName)) {
                System.out.println("Return cached " + service + " object");
                return  service;
            }
        }
        return  null;
    }

    public void addService(Service newService) {
        boolean exists = false;
        for (Service service : services) {
            if (service.getName().equalsIgnoreCase(newService.getName())) {
                exists = true;
            }
        }
        if (!exists) services.add(newService);
    }
}
