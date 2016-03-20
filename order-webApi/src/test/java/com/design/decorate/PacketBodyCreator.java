package com.design.decorate;

/**
 * Created by zhangxiong on 16/3/4.
 */
public class PacketBodyCreator implements IPacketCreator {

    public PacketBodyCreator() {
        System.out.println(this);
    }

    public String handlerContent() {
        return "Content of Packet";
    }
}
