package com;

/**
 * Created by zhangxiong on 16/3/22.
 */
public class StaticTest {
    public static long date = System.currentTimeMillis();
    public long da = System.currentTimeMillis();

    {

        System.out.println("block:" + System.currentTimeMillis());


    }

    static {
        try {
            Thread.sleep(2000l);
            System.out.println("static block:" + System.currentTimeMillis());
        } catch (Exception e) {

        }

    }

    public StaticTest() {
        System.out.println("construct:" + System.currentTimeMillis());
    }

    static class inner {
        public static long date1 = System.currentTimeMillis();
    }

    public long getDate() {
        return inner.date1;
    }

    public static void main(String[] args) throws InterruptedException {
//        Map concurrentMap = new ConcurrentHashMap<String, Object>();
//        concurrentMap.put("name", "zhangxiongn");
//        System.out.println(concurrentMap);

//        System.out.println(date);
//        Thread.sleep(222l);
//        StaticTest test1 = new StaticTest();
//        System.out.println("da====" + test1.da);
//        Thread.sleep(20l);
//        StaticTest test2 = new StaticTest();
//        System.out.println("da====" + test2.da);
//        System.out.println(test1.getDate());
//        Thread.sleep(111l);
//        System.out.println(test2.getDate());

    }


}

