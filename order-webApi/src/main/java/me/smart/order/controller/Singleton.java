package me.smart.order.controller;

/**
 * Created by zhangxiong on 16/1/24.
 */
public class Singleton {

    private static Singleton singleton;

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;

    }
}
;