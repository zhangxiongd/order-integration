package com.design.proxy.springaop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by zhangxiong on 16/3/29.
 */
public class AfterAdvice3 implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice3.afterReturning() execute ");
    }
}
