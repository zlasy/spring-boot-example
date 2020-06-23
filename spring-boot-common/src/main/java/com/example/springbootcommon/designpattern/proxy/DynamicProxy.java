package com.example.springbootcommon.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object stu;

    public DynamicProxy(Object stu) {
        this.stu = stu;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before Invoke Method:"+method);
        method.invoke(stu, args);
        System.out.println("After Invoke Method");
        return null;
    }

    public static void main(String[] args) {
        Person realStu = new Student();
        InvocationHandler handler = new DynamicProxy(realStu);

        Person proxyStu = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realStu.getClass().getInterfaces(), handler);
        System.out.println(proxyStu.getClass().getName());
        proxyStu.giveMoney("李四");
        proxyStu.money();
    }
}
