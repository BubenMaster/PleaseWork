package com.yoj.collections.level2.part8.ProxyGenerics;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyGenericsTest {
    public static void main(String[] args) {
        ProxyGenericsTest solution = new ProxyGenericsTest();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }



        private static void test(Object proxy) {
            boolean isItem = proxy instanceof Item;
            boolean isBig = proxy instanceof Big;
            boolean isSmall = proxy instanceof Small;

            System.out.format("%b %b %b\n", isItem, isBig, isSmall);
        }


    public <T extends Item> T getProxy(Class<T> type, Class<?>... interfaces ) {
        Class<?>[] allInterfaces = new Class[interfaces.length+1];
        System.arraycopy(interfaces, 0, allInterfaces,0,interfaces.length);
        allInterfaces[allInterfaces.length-1] = type;

        InvocationHandler invocationHandler = new ItemInvocationHandler();
        ClassLoader classLoader = this.getClass().getClassLoader();
        return (T) Proxy.newProxyInstance(classLoader, allInterfaces, invocationHandler);
    }
}
