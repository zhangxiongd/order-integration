package com.design.decorate;

/**
 * Created by zhangxiong on 16/3/4.
 */
public class MainTest {
    public static void main(String[] args) {
        IPacketCreator iPacketCreator = new HeaderPacketDecorator(new HtmlPacketDecorator(new PacketBodyCreator()));
        System.out.println(iPacketCreator.handlerContent());
    }
}
