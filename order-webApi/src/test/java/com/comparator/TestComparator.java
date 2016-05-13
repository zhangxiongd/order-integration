package com.comparator;

import java.util.*;

/**
 * Created by zhangxiong on 16/3/6.
 */
public class TestComparator {
    public static void main(String[] args) {
        /**
         * 对象实现comparable 然后在用Collections.sort()排序
         * 集合内部比较
         //         */
   /*     List<UserComparable> list = new ArrayList<UserComparable>();
        list.add(new UserComparable("zhangxiong", 28));
        list.add(new UserComparable("chenqin", 25));
        Collections.sort(list);*/

//
        List<User> list2 = new ArrayList<>();
        list2.add(new User("zhangxiong", 28));
        list2.add(new User("chenqin", 25));
        list2.add(new User("lihua", 26));
        /**
         *
         */
//        MyComparator myComparator = new MyComparator();
//        Collections.sort(list2, myComparator);
//        for (User user : list2) {
//            System.out.println(user.getName());
//        }

        /**
         *
         */


        SortedSet<User> set = new TreeSet<>(new Comparator<User>() {
            public int compare(User o1, User o2) {
                System.out.println(o1.getAge().compareTo(o2.getAge()));
                return o1.getAge().compareTo(o2.getAge()) > 0 ? 1 : -1;
            }
        });

        set.addAll(list2);
        for (User user : list2) {
            System.out.println(user.getName());
        }
    }

}
