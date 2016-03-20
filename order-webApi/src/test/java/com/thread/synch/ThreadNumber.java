package com.thread.synch;

/**
 * Created by zhangxiong on 16/3/4.
 */
public class ThreadNumber implements Runnable {

    private String[] characterArray;

    public ThreadNumber(String[] characterArray) {
        this.characterArray = characterArray;
    }

    public void run() {
        try {

            for (int i = 0, len = 52; i < len; i++) {

                if (i != 0 && i % 2 == 0) {
                    synchronized (characterArray) {
                        characterArray.notify();
                        characterArray.wait();
                    }
                }
                System.out.print(i + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


