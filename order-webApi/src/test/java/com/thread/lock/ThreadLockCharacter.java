package com.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangxiong on 16/3/5.
 */
public class ThreadLockCharacter implements Runnable {

    private Lock lock;
    private Condition conditionNumber;
    private Condition conditionCharacter;
    private String currentThread;

    public ThreadLockCharacter(Lock lock, Condition conditionNumber, Condition conditionCharacter, String currentThread) {
        this.lock = lock;
        this.conditionNumber = conditionNumber;
        this.conditionCharacter = conditionCharacter;
        this.currentThread = currentThread;
    }

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
