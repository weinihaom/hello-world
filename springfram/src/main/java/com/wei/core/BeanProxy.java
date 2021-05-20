package com.wei.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BeanProxy implements InvocationHandler {
    private Object bean;

    public BeanProxy() {
    }

    public BeanProxy(Object bean) {
        this.bean = bean;
    }


    /**
     * @param proxy  代理对象
     * @param method 方法
     * @param args   参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before:" + method.getName());
        Object result = method.invoke(bean, args);
        System.out.println("after:" + method.getName());
        return result;
    }

}
