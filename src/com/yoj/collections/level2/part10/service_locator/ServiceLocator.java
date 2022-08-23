package com.yoj.collections.level2.part10.service_locator;

import com.yoj.collections.level2.part10.service_locator.context.InitialContext;
import com.yoj.collections.level2.part10.service_locator.service.Service;

public class ServiceLocator {
    private static final Cache cache;

    private static Service currentService = null;

    static {
        cache = new Cache();
    }



    /**
     * First, check for a service object in the cache.
     * <p> If a service object is not in the cache, perform a lookup using
     * the JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of the service object in the context
     * @return Object mapped to the name in context
     */
    public static Service getService(String jndiName){
        if (serviceIsInCache(jndiName)) {
            return currentService;
        }
        else {
            currentService = createContextService(jndiName);
            addServiceToCache(currentService);
            return currentService;
        }
    }

    private static void addServiceToCache(Service contextService) {
        cache.addService(contextService);
    }

    private static boolean serviceIsInCache(String jndiName){
        currentService = cache.getService(jndiName);
        return  currentService != null;
    }

    private static Service createContextService(String jndiName) {
        return (Service) new InitialContext().lookup(jndiName);
    }


}
