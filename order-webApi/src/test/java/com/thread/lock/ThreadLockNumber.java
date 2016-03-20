package com.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangxiong on 16/3/5.
 */
public class ThreadLockNumber implements Runnable {

    private Lock lock;
    private Condition conditionNumber;
    private Condition conditionCharacter;

    private String currentThread;

    public ThreadLockNumber(Lock lock, Condition conditionNumber, Condition conditionCharacter, String currentThread) {
        this.lock = lock;
        this.conditionNumber = conditionNumber;
        this.conditionCharacter = conditionCharacter;
        this.currentThread = currentThread;
    }

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
