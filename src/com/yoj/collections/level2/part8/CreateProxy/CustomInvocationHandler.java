package com.yoj.collections.level2.part8.CreateProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    SomeInterfaceWithMethods implementation;

    public CustomInvocationHandler(SomeInterfaceWithMethods implementation) {
        this.implementation = implementation;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " in");

        Object obj = method.invoke(implementation,args);

        System.out.println(method.getName() + " out");
        return obj;
    }
}
