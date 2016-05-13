package com.design.proxy.jdkDynamic;

/**
 * Created by zhangxiong on 16/3/27.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add(Integer userId, String userName) {
        System.out.println("=======UserServiceImpl.add()=======");
    }

    @Override
    public void delete(Integer userId) {
        System.out.println("======UserServiceImpl.delete()=======");
    }
}
