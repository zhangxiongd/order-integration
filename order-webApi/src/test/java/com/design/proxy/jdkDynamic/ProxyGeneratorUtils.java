package com.design.proxy.jdkDynamic;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by zhangxiong on 16/3/27.
 */
public class ProxyGeneratorUtils {
    public static void writeProxyClassToHardDisk(Class clazz, String path) {
        byte[] classFile = ProxyGenerator.generateProxyClass("$ProxySelf", clazz.getInterfaces());

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            outputStream.write(classFile);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {

            }


        }

    }
}
