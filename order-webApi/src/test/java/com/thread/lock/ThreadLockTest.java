package com.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangxiong on 16/3/5.
 */
public class ThreadLockTest {

    private final Lock lock = new ReentrantLock();
    private final Condition conditionNumber = lock.newCondition();
    private final Condition conditionCharacter = lock.newCondition();
    static String currentThread = "A";

    public static void main(String[] args) {
//        ThreadLockTest test = new ThreadLockTest();
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(test.new ThreadLockNumber());
//        executorService.execute(test.new ThreadLockCharacter());
//        executorService.shutdown();
        String a = "abv";
        String b = "ab" + new String("v");
        System.out.println(a == b);

    }


    private class ThreadLockNumber implements Runnable {
        public void run() {
            try {
                for (int i = 1; i <= 52; i++) {
                    lock.lock();
                    while (currentThread != "A") {
                        try {
                            conditionNumber.await();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    System.out.print(i);
                    if (i % 2 == 0) {
                        currentThread = "B";
                        conditionCharacter.signal();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }


    private class ThreadLockCharacter implements Runnable {

        public void run() {
            try {
                for (char i = 'A'; i <= 'Z'; i++) {
                    lock.lock();
                    while (currentThread != "B") {
                        try {
                            conditionCharacter.await();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }
                    System.out.print(i);
                    currentThread = "A";
                    conditionNumber.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}
