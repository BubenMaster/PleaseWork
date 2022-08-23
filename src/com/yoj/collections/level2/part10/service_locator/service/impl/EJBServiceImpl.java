package com.yoj.collections.level2.part10.service_locator.service.impl;

import com.yoj.collections.level2.part10.service_locator.service.Service;

public class EJBServiceImpl implements Service {
    @Override
    public String getName() {
        return "EJBService";
    }

    @Override
    public void execute() {
        System.out.println("Executing the EJBService");

    }
}
