package com.thread.lock;

/**
 * Created by zhangxiong on 16/3/5.
 */
public class LockTest {
    private volatile static String currentThread = "A";


    public static void main(String[] args) {
//        Lock lock = new ReentrantLock();
//        Condition conditionNumber = lock.newCondition();
//        Condition conditionCharacter = lock.newCondition();
//        ThreadLockNumber threadLockNumber = new ThreadLockNumber(lock, conditionNumber, conditionCharacter, currentThread);
//        ThreadLockCharacter threadLockCharacter = new ThreadLockCharacter(lock, conditionNumber, conditionCharacter, currentThread);
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        executorService.execute(threadLockNumber);
//        executorService.execute(threadLockCharacter);
//        executorService.shutdown();

        short s1 = 1;
        int s2 = s1 + 1;
        s1 += 1;
        System.out.println(s1);
        long a = 3;
        float b = a;

    }
}
