package com.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangxiong on 16/3/5.
 */
public class LockTest {
    private volatile static String currentThread = "A";


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionNumber = lock.newCondition();
        Condition conditionCharacter = lock.newCondition();
        ThreadLockNumber threadLockNumber = new ThreadLockNumber(lock, conditionNumber, conditionCharacter, currentThread);
        ThreadLockCharacter threadLockCharacter = new ThreadLockCharacter(lock, conditionNumber, conditionCharacter, currentThread);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(threadLockNumber);
        executorService.execute(threadLockCharacter);
        executorService.shutdown();



    }
}
