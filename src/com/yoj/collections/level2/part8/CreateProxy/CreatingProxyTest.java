package com.yoj.collections.level2.part8.CreateProxy;

import java.lang.reflect.Proxy;

public class CreatingProxyTest {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);
        obj.stringMethodWithoutArgs();
    }

    private static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethodsImpl origin = new SomeInterfaceWithMethodsImpl();

        ClassLoader loader = SomeInterfaceWithMethodsImpl.class.getClassLoader();
        Class[] interfaces = new Class[]{SomeInterfaceWithMethods.class};
        CustomInvocationHandler invocationHandler = new CustomInvocationHandler(origin);

        return (SomeInterfaceWithMethods) Proxy.newProxyInstance(loader, interfaces, invocationHandler);
    }
}
