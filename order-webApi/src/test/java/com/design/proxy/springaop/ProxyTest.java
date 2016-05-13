package com.design.proxy.springaop;

import com.comparator.User;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by zhangxiong on 16/3/29.
 */
public class ProxyTest {

    @Test
    public void proxyTest() {
        ProxyFactory proxyFactory = new ProxyFactory();

        proxyFactory.addAdvice(new AfterAdvice2());
        proxyFactory.addAdvice(new AfterAdvice3());
        proxyFactory.addAdvice(new BeforeAdvice3());
        proxyFactory.addAdvice(new BeforeAdvice2());
        //target class
        LoginService loginService = new LoginServiceImpl();
        proxyFactory.setTarget(loginService);
        proxyFactory.setInterfaces(new Class[]{LoginService.class});
        LoginService proxyService = (LoginService) proxyFactory.getProxy();
        proxyService.login(new User("zhangxiong", 12));
    }
}
