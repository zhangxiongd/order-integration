package com.thread.synch;

/**
 * Created by zhangxiong on 16/3/5.
 */
public class ThreadTest {
    public static void main(String[] args) {
        String[] characters = getCharacters();

        ThreadNumber threadNumber = new ThreadNumber(characters);
        ThreadCharacter threadCharacter = new ThreadCharacter(characters);
        new Thread(threadNumber).start();
        new Thread(threadCharacter).start();

    }


    private static String[] getCharacters() {
        String[] characters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return characters;
    }


}
