package com;

import me.smart.order.model.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhangxiong on 16/3/7.
 */
public class StringUtil {

    /**
     * 获取两个字符串中最长的公共字符串
     * 比如first ＝ “abcdabc” seconde="abcdefgh" 返回"abcdefg"
     *
     * @param first
     * @param second
     * @return
     */
    public static String droup(String first, String second) {
        if (second.contains(first)) {
            return first;
        }
        List<String> list = new ArrayList<String>();
        //获取
        for (int i = 0, len = first.length(); i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String str = first.substring(i, j);
                if (second.contains(str)) {
                    list.add(str);
                }
            }
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length() > o2.length() ? 1 : -1;
            }
        });
        return list.get(list.size() - 1);
    }


    public static void main(String[] args) {
//        System.out.println(dup("abcdabc", "abcdefgh"));
//        System.out.println("bcdefgh".contains("bcd"));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(6);
        System.out.println(list.toString());
        for (Integer i : list) {
            if (i == 3) {
                i = 3 * 3;
            }
        }
        System.out.println(list.toString());


        List<Member> list1 = new ArrayList<>();
        Member member1 = new Member();
        member1.setName("zhangxiong");
        member1.setCity("shanghai");
        list1.add(member1);
        System.out.println(list1);
        for (Member member : list1) {
            if (member.getName().equals("zhangxiong")) {
                member.setCity("changsha");
            }
        }
        System.out.println(list1);


        List<String> list2 = new ArrayList<>();
        list2.add("zhangxiong");
        list2.add("chenqin");
        for (String str : list2) {
            if (str.equals("chenqin")) {
                str = "love chenqin";
            }
        }
        System.out.println(list2.toString());


        Integer[] array = new Integer[]{1, 3, 5, 63, 8};
        for (Integer i : array) {
            if (i == 5) {
                i = 5 * 2;
            }
        }
        for (Integer i : array) {
            System.out.println(i);
        }


        /**
         * lamb
         */
        list2.stream().forEach(item -> {
            if (item.equals("zhangxiong")) {
                System.out.println("====");
                item = "123456";
            }
        });
        System.out.println(list2);

    }

    public static String dup(String first, String second) {
        //比较两个字符串的大小

        if (first.length() >= second.length()) {
            String midStr = first;
            first = second;
            second = midStr;
        }
        if (second.contains(first)) {
            return first;
        }
        List<String> list = new ArrayList<String>();
        return list.toString();
    }

}
