package com.thread;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxiong on 2016-11-20.
 */
public class ThreadLocalTest {
    public static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };


    public static final void begin(){
         TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception{
        ThreadLocalTest.begin();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Costs : "+ ThreadLocalTest.end() +" ms");
    }
}
