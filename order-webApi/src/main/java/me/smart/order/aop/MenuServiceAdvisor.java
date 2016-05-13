package me.smart.order.aop;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhangxiong on 16/3/29.
 */
@Component("menuServiceAdvisor")
public class MenuServiceAdvisor implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("method name" + method.getName());
        System.out.println(args[0].toString());
        System.out.println(target.toString());

        System.out.println("===============method before");
    }
}
