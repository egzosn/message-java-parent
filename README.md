###message-java-parent


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