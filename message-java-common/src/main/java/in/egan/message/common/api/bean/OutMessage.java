package in.egan.message.common.api.bean;

import in.egan.message.common.enums.MessageType;

import java.io.Serializable;

/**
 *  支付宝支付通知
 * @author  egan
 * @email egzosn@gmail.com
 * @date 2016-10-10 11:10:30
 */
public abstract class OutMessage implements Serializable {
    protected String content;
    protected MessageType msgType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public abstract String toMessage();
}
