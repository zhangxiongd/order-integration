package com.design.proxy.springaop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zhangxiong on 16/3/29.
 */
public class BeforeAdvice3 implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getName());
        System.out.println("BeforeAdvice3.before() execute ");

    }
}
