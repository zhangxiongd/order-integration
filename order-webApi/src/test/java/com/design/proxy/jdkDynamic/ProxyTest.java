package com.design.proxy.jdkDynamic;

import org.junit.Test;

/**
 * Created by zhangxiong on 16/3/27.
 */
public class ProxyTest {

    //    @Test
    public void testAdd() {
        //实例化目标对象
        UserService userService = new UserServiceImpl();

        //实例化MyInvocationHandler
        MyInvocationHandler handler = new MyInvocationHandler(userService);

        UserService proxy = (UserService) handler.getProxy();

        proxy.add(1, "zhangxiong");

    }


    @Test
    public void testWriteProxyClassToHardDisk() {
        ProxyGeneratorUtils.writeProxyClassToHardDisk(UserServiceImpl.class, "/Users/zhangxiong/data/$Proxy11.class");
    }
}
