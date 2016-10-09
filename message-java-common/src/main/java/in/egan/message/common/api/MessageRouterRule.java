package in.egan.message.common.api;


import in.egan.message.common.api.bean.OutMessage;
import in.egan.message.common.api.exception.MessageErrorException;
import in.egan.message.common.enums.MessageEvent;
import in.egan.message.common.enums.MessageType;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 消息路由规则
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2016/9/30 15:10
 */
public class MessageRouterRule {


    /**
     * 消息路由
     */
    private final MessageRouter messageRouter;

    private MessageType msgType;
    private List<MessageEvent> events = new ArrayList<>();
    private String eventKey;
    private boolean async = false;
    private String discount;
    private String rDiscount;
    private String subject;
    private String rSubject;
    private String content;
    private String rContent;
    private boolean reEnter = false;
    /**
     * 消息观察者
     */
    private List<MessageHandler> handlers = new ArrayList<MessageHandler>();
    private List<MessageInterceptor> interceptors = new ArrayList<MessageInterceptor>();


    public MessageRouterRule(MessageRouter messageRouter) {
        this.messageRouter = messageRouter;
    }

    /**
     * 设置是否异步执行，默认是false
     *
     * @param async
     * @return
     */
    public MessageRouterRule async(boolean async) {
        this.async = async;
        return this;
    }

    /**
     * 如果msgType等于某值
     *
     * @param msgType
     * @return
     */
    public MessageRouterRule msgType(MessageType msgType) {
        this.msgType = msgType;
        return this;
    }

    /**
     * 如果event等于某值
     *
     * @param event
     * @return
     */
    public MessageRouterRule event(MessageEvent event) {
        return event(event, (MessageEvent)null);
    }

    /**
     * 如果event等于某值
     *
     * @param event 事件
     * @param otherEvent 附加事件
     * @see MessageEvent 消息事件
     * @return
     */
    public MessageRouterRule event(MessageEvent event, MessageEvent ...otherEvent) {
        events.add(event);
        if (null == otherEvent || otherEvent.length <= 0){
            return this;
        }

        for (MessageEvent i : otherEvent){
            events.add(i);
        }
        return this;
    }

    /**
     * 如果eventKey等于某值
     *
     * @param eventKey
     * @return
     */
    public MessageRouterRule eventKey(String eventKey) {
        this.eventKey = eventKey;
        return this;
    }

    /**
     * 如果discount等于某值
     *
     * @param discount
     * @return
     */
    public MessageRouterRule discount(String discount) {
        this.discount = discount;
        return this;
    }

    /**
     * 如果discount匹配该正则表达式
     *
     * @param regex
     * @return
     */
    public MessageRouterRule rDiscount(String regex) {
        this.rDiscount = regex;
        return this;
    }

    /**
     * 如果discount等于某值
     *
     * @param subject
     * @return
     */
    public MessageRouterRule subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * 如果discount匹配该正则表达式
     *
     * @param regex
     * @return
     */
    public MessageRouterRule rSubject(String regex) {
        this.rSubject = regex;
        return this;
    }


    /**
     * 设置消息拦截器
     *
     * @param interceptor
     * @return
     */
    public MessageRouterRule interceptor(MessageInterceptor interceptor) {
        return interceptor(interceptor, (MessageInterceptor[]) null);
    }

    /**
     * 设置消息拦截器
     *
     * @param interceptor
     * @param otherInterceptors
     * @return
     */
    public MessageRouterRule interceptor(MessageInterceptor interceptor, MessageInterceptor... otherInterceptors) {
        this.interceptors.add(interceptor);
        if (otherInterceptors != null && otherInterceptors.length > 0) {
            for (MessageInterceptor i : otherInterceptors) {
                this.interceptors.add(i);
            }
        }
        return this;
    }

    /**
     * 设置消息处理器
     *
     * @param handler
     * @return
     */
    public MessageRouterRule handler(MessageHandler handler) {
        return handler(handler, (MessageHandler[]) null);
    }

    /**
     * 设置消息处理器
     *
     * @param handler
     * @param otherHandlers
     * @return
     */
    public MessageRouterRule handler(MessageHandler handler, MessageHandler... otherHandlers) {
        this.handlers.add(handler);
        if (otherHandlers != null && otherHandlers.length > 0) {
            for (MessageHandler i : otherHandlers) {
                this.handlers.add(i);
            }
        }
        return this;
    }

    /**
     *  设置处理器的配置文件
     * @param configStorage 配置信息
     * @return
     */
    public MessageRouterRule configStorage(MessageConfigStorage configStorage){

        return configStorage(configStorage, (MessageConfigStorage[]) null);
    }

    /**
     *  设置处理器的配置文件
     * @param configStorage 配置信息
     * @return
     */
    public MessageRouterRule configStorage(MessageConfigStorage configStorage, MessageConfigStorage ... otherConfigStorage){
        if ( this.handlers.isEmpty()){
            return this;
        }
        // TODO 2016/10/9 10:42 author: egan  这部分还有一些bug
        List<MessageConfigStorage> messageConfigStorages = new ArrayList<MessageConfigStorage>();
        messageConfigStorages.add(configStorage);
        if (otherConfigStorage != null && otherConfigStorage.length > 0) {
            for (MessageConfigStorage i : otherConfigStorage) {
                messageConfigStorages.add(i);
            }
        }

        for (MessageHandler handler : handlers){
            handler.configStorage(messageConfigStorages);
        }
//        configStorages.put(handlers.get(0), messageConfigStorages);
        return this;
    }

    public MessageRouterRule reEnter(boolean reEnter){
        this.reEnter = reEnter;
        return this;
    }
    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     *
     * @return
     */
    public MessageRouter end() {
        this.messageRouter.getRules().add(this);
        return this.messageRouter;
    }

    /**
     * 规则结束，但是消息还会进入其他规则
     *
     * @return
     */
    public MessageRouter next() {
        this.reEnter = true;
        return end();
    }

    /**
     * 将消息事件修正为不区分大小写,
     * 比如框架定义的事件常量为
     * @param message
     * @return
     */
    protected boolean test(Message message) {


        return (
                (null == this.msgType || null == message.getMsgTypes() || message.getMsgTypes().contains(msgType))
                        &&
                        (this.events.isEmpty() || null == message.getEvent() || this.events.contains((message.getEvent())))
                        &&
                        (null == this.eventKey || this.eventKey.toLowerCase().equals((null == message.getEventKey() ? null : message.getEventKey().toLowerCase())))
                        &&
                        (null == this.subject || null == message.getSubject() || this.subject.contains(message.getSubject()))
                        &&
                        (null == this.rSubject|| Pattern.matches(this.rSubject, null == message.getSubject() ? "" : message.getSubject().trim()))
                        &&
                        (null == this.content|| this.content.equals(null == message.getContent() ? null : message.getContent().trim()))
                        &&
                        (null == this.rContent || Pattern
                                .matches(this.rContent, null == message.getContent() ? "" : message.getContent().trim()))


        );
    }

    /**
     * 处理消息回调过来的消息
     *
     * @param message
     * @return true 代表继续执行别的router，false 代表停止执行别的router
     */
    protected List<OutMessage> service(Message message,
                                       MessageErrorExceptionHandler exceptionHandler) {

        try {

            Map<String, Object> context = new HashMap<String, Object>();
            // 如果拦截器不通过
            for (MessageInterceptor interceptor : this.interceptors) {
                if (!interceptor.intercept(message, context)) {
                    return null;
                }
            }

            // 交给handler处理
            List<OutMessage> res = new ArrayList<>();
            for (MessageHandler handler : this.handlers) {
                // 返回最后handler的结果
                res.add(handler.handle(message, context));
            }
            return res;
        } catch (MessageErrorException e) {
            exceptionHandler.handle(e);
        }
        return null;

    }

    public MessageRouter getRouterBuilder() {
        return messageRouter;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }



    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public List<MessageEvent> getEvents() {
        return events;
    }

    public void setEvents(List<MessageEvent> events) {
        this.events = events;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getrDiscount() {
        return rDiscount;
    }

    public void setrDiscount(String rDiscount) {
        this.rDiscount = rDiscount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getrSubject() {
        return rSubject;
    }

    public void setrSubject(String rSubject) {
        this.rSubject = rSubject;
    }

    public boolean isReEnter() {
        return reEnter;
    }

    public void setReEnter(boolean reEnter) {
        this.reEnter = reEnter;
    }

    public List<MessageHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<MessageHandler> handlers) {
        this.handlers = handlers;
    }

    public List<MessageInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<MessageInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

}
