package com.design.proxy.springaop;

import com.comparator.User;

/**
 * Created by zhangxiong on 16/3/29.
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public void login(User user) {
        System.out.println("=====user login");
    }
}
