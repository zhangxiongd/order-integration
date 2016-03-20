package com.design.decorate;

/**
 * 加请求头header信息
 * Created by zhangxiong on 16/3/4.
 */
public class HeaderPacketDecorator extends PacketDecorator {

    public HeaderPacketDecorator(IPacketCreator component) {
        super(component);
    }

    /**
     * 给html加请求头
     *
     * @return
     */
    public String handlerContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Header Info Content-type\n");
        stringBuilder.append(component.handlerContent());
        return stringBuilder.toString();
    }
}
