package com.design.decorate;

/**
 * Created by zhangxiong on 16/3/4.
 * 装饰者
 */
public abstract class PacketDecorator implements IPacketCreator {
    IPacketCreator component;

    public PacketDecorator(IPacketCreator component) {
        this.component = component;
    }
}
