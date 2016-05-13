package com.design.singleton;

/**
 * Created by zhangxiong on 16/1/24.
 */
public class Singleton {

    /**
     * 双层检查模式
     */
//    private volatile Singleton singleton = null;
//
//    public Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (this) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }

    /**
     * 内部类
     * 一个私有的构造函数
     * 有个私有的内部类，再内部类实例化这个单件对象
     */

    private Singleton() {

    }

    private static class InnerSingleton {
        private static final Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        System.out.println("getInstane......");
        return InnerSingleton.singleton;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("========");
        Thread.sleep(3000l);
        System.out.println(Singleton.getInstance());
    }
}
