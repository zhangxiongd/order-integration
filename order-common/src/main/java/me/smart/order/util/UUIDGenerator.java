package me.smart.order.util;

import java.util.UUID;

public class UUIDGenerator {

    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(get32UUID());
    }
}
