package com;

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
        System.out.println(dup("abcdabc", "abcdefgh"));
//        System.out.println("bcdefgh".contains("bcd"));
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
