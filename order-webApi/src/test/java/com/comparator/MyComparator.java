package com.comparator;

import java.util.Comparator;

/**
 * Created by zhangxiong on 16/3/6.
 */
public class MyComparator implements Comparator<User> {
    public int compare(User o1, User o2) {
        return o1.getAge() > o2.getAge() ? 1 : -1;
    }
}
