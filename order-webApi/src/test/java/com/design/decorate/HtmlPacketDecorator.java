package com.design.decorate;

/**
 * Created by zhangxiong on 16/3/4.
 */
public class HtmlPacketDecorator extends PacketDecorator {

    public HtmlPacketDecorator(IPacketCreator component) {
        super(component);
    }

    /**
     * 处理content
     *
     * @return
     */
    public String handlerContent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<body>");
        stringBuilder.append(component.handlerContent());
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
}
