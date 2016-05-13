package com.design.proxy.springaop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * Created by zhangxiong on 16/3/29.
 */
@Order(value = Integer.MAX_VALUE)
public class AfterAdvice2 implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice2.afterReturning() execute ");
    }
}
