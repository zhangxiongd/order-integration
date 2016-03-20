package com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhangxiong on 16/1/29.
 */
public class FileWR {

    public static void main(String[] args) {
        try {
            //   fileCopy("/Users/zhangxiong/work/wait_wopy.txt", "/Users/zhangxiong/work/copyed.txt");
            flipSwitch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件复制功能
     *
     * @param source
     * @param destination
     */
    public static void fileCopy(String source, String destination) throws Exception {
        //输入流，将文件读入内存中
        FileInputStream inputStream = new FileInputStream(source);
        //输出流，将内存的内容写入文件
        FileOutputStream outputStream = new FileOutputStream(destination);

        //获取输入流和输出流的通道Channel
        FileChannel readChannel = inputStream.getChannel();

        FileChannel writeChannel = outputStream.getChannel();

        //创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        while (true) {
            byteBuffer.clear();
            int len = readChannel.read(byteBuffer);
            if (len == -1) {
                break;
            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
        }
        readChannel.close();
        writeChannel.close();
    }


    /**
     * flip切换导致position limit capacity mark 状态的变化
     * position始终指向待读的一位或者待写的一位
     */
    private static void flipSwitch() {
        //从heap 中分配内存
        ByteBuffer buffer = ByteBuffer.allocate(15);
        System.out.println("After init");
        System.out.println("position=" + buffer.position() + " ,limit=" + buffer.limit() + " ,capacity=" + buffer.capacity());

//
//
//        for (int i = 0; i < 5; i++) {
//            System.out.print(buffer.get());
//            System.out.printf("  after get" + i + ", now position=" + buffer.position());
//        }
//
//        System.out.println("After get 5 values");
//        System.out.println("position=" + buffer.position() + " ,limit=" + buffer.limit() + " ,capacity=" + buffer.capacity());
//
//
//        buffer.flip();
//        System.out.println("After second flip");
//        System.out.println("position=" + buffer.position() + " ,limit=" + buffer.limit() + " ,capacity=" + buffer.capacity());
//
//
//        buffer.clear();
//        System.out.println("After clear");
//        System.out.println("position=" + buffer.position() + " ,limit=" + buffer.limit() + " ,capacity=" + buffer.capacity());    for (int i = 0; i < 10; i++) {
//        buffer.put((byte) i);
//            System.out.printf("  after put" + i + ", now position=" + buffer.position());
//        }
//
//
//        System.out.println("After put value");
//        System.out.println("position=" + buffer.position() + " ,limit=" + buffer.limit() + " ,capacity=" + buffer.capacity());
//
//        buffer.flip();
//        System.out.println("After first flip");
//        System.out.println("position=" + buffer.position() + " ,limit=" + buffer.limit() + " ,capacity=" + buffer.capacity());


        System.out.println("now buffer capacity" + buffer.capacity());
        try {
            for (int i = 0; i < 5; i++) {
                buffer.put((byte) 9);

                System.out.println("   now position=" + buffer.position());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        buffer.rewind();
        System.out.print(buffer.position());
        System.out.print(buffer.limit());
        while (true) {
            System.out.println(buffer.hasRemaining());
            if (!buffer.hasRemaining()) {
                break;
            }
            System.out.print(buffer.get());
        }


        byte[] array = new byte[5];
        buffer.get(array);

        for (int i = 0; i < 15; i++) {
            System.out.println(array[0]);
        }

    }
}
