package in.egan.message.test;

import in.egan.message.common.api.Message;
import in.egan.message.common.api.MessageConfigStorage;
import in.egan.message.common.api.MessageRouter;
import in.egan.message.common.enums.MessageEvent;
import in.egan.message.common.enums.MessageType;
import in.egan.message.test.api.config.AppPushMessageConfigStorage;
import in.egan.message.test.api.config.EmailMessageConfigStorage;
import in.egan.message.test.api.config.SMSMessageConfigStorage;
import in.egan.message.test.api.handler.AppPushMessage;
import in.egan.message.test.api.handler.EmailMessage;
import in.egan.message.test.api.handler.SMSMessage;
import in.egan.message.test.api.handler.WebMessage;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 邮件消息的系统配置
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/10/09 14:56
 */
public class Test extends Observable{
    public Test() {
        setChanged();
    }

    public static void main(String[] args) {


        MessageRouter messageRouter = new MessageRouter();

        messageRouter
                .rule()
                .async(false)
                .msgType(MessageType.App)
                .event(MessageEvent.unicast)
                .handler(new AppPushMessage())
                .configStorage(new AppPushMessageConfigStorage("http://msg.umeng.com/api/send", "xxxxxxxxxxxxx", "xxxxxxxxxxxxxxx", MessageType.App),new AppPushMessageConfigStorage("http://msg.umeng.com/api/send", "xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxx", MessageType.App))
                .next()
                .rule()
                .async(false)
                .msgType(MessageType.Email)
                .event(MessageEvent.unicast)
                .handler(new EmailMessage())
                .configStorage(new EmailMessageConfigStorage("http://email.com", "user", "pwd", MessageType.Email))
                .reEnter(true)
                .next()
                .rule()
                .async(false)
                .msgType(MessageType.SMS)
                .event(MessageEvent.unicast, MessageEvent.batch)
                .handler(new SMSMessage())
                .configStorage(new SMSMessageConfigStorage("http://api.chanzor.com/send", "xxxxx", "xxxxxxxxxxxxxxxxx", "", true, MessageType.SMS))
                .reEnter(true)
                .next()
                .rule()
                .async(false)
                .msgType(MessageType.SMS)
                .event(MessageEvent.radio)
                .handler(new SMSMessage())
                .configStorage(new SMSMessageConfigStorage("http://api.chanzor.com/send", "xxxxxx", "xxxxxxxxxxxxxxxxxxxx1", "", true, MessageType.SMS))
                .reEnter(true)
                .next()
                .rule()
                .async(false)
                .event(MessageEvent.radio)
                .msgType(MessageType.Web)
                .handler(new WebMessage())
                .end()
        ;
        List<MessageType> messageTypes = new ArrayList<>();
        messageTypes.add(MessageType.App);
        messageTypes.add(MessageType.SMS);
        Message message = new Message("user", "to", messageTypes, "title", "content");
        message.setEvent(MessageEvent.unicast);
        messageRouter.route(message);
    }

}
