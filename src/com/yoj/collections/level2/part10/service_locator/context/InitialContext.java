package com.yoj.collections.level2.part10.service_locator.context;

import com.yoj.collections.level2.part10.service_locator.service.impl.EJBServiceImpl;
import com.yoj.collections.level2.part10.service_locator.service.impl.JMSServiceImpl;

public class InitialContext {
    public Object lookup(String jndiName) {

        if (jndiName.equalsIgnoreCase("EJBService")) {
            System.out.println("Looking up and creating a new EJBService object");
            return new EJBServiceImpl();
        } else if (jndiName.equalsIgnoreCase("JMSService")) {
            System.out.println("Looking up and creating a new JMSService object");
            return new JMSServiceImpl();
        }
        return null;
    }
}
