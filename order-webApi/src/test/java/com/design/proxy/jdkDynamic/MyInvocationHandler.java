package com.design.proxy.jdkDynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangxiong on 16/3/27.
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 目标对象  委托给MyInvocationHandler
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * 执行目标方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.toString());
        System.out.println("method name=" + method.getName());
        for (Object o : args) {
            System.out.println("method args ====" + o);
        }
        System.out.println("=========start before==========");
        Object result = method.invoke(target, args);
        System.out.println("=========end after=============" + result);
        return result;
    }


    public Object getProxy() {
        for (Class c : target.getClass().getInterfaces()) {
            System.out.println(c.getName());
        }
        Object object = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
        System.out.println(object.getClass());
        return object;

    }
}
