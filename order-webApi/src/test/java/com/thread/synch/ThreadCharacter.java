package com.thread.synch;

/**
 * Created by zhangxiong on 16/3/4.
 */
public class ThreadCharacter implements Runnable {
    private String[] characterArray;
    
    public ThreadCharacter(String[] characterArray) {
        this.characterArray = characterArray;
    }

    public void run() {
        try {
            for (int i = 0, len = characterArray.length; i < len; i++) {
                synchronized (characterArray) {
                    System.out.print(characterArray[i]);
                    characterArray.notify();
                    characterArray.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
