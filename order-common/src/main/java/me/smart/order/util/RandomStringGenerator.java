package me.smart.order.util;

import java.util.Random;

/**
 * Created by zhangxiong on 16/1/17.
 */
public class RandomStringGenerator {

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    /**
     * 生成短信
     *
     * @param length
     * @return
     */
    public static String getVerifyCode() {
        String base = "01234567899876543210";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        String nowTime = String.valueOf(System.currentTimeMillis());
        for (int i = 0; i < 2; i++) {
            char number = nowTime.charAt(random.nextInt(nowTime.length()));
            sb.append(number);
        }
        return sb.toString();
    }
}
