package in.egan.message.common.api;

import in.egan.message.common.api.bean.OutMessage;
import in.egan.message.common.api.exception.MessageErrorException;

import java.util.List;
import java.util.Map;


/**
 * 处理回调消息的处理器接口
 * @author egan
 * @email egzosn@gmail.com
 * @date 2016-6-1 11:40:30
 */
public interface MessageHandler {

    /**
     * @param message
     * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @return xml,text格式的消息，如果在异步规则里处理的话，可以返回null
     */
    public OutMessage handle(Message message,  Map<String, Object> context) throws MessageErrorException;

    /**
     *  设置回调的配置信息
     * @param configStorages 配置信息
     */
    public void configStorage(List<MessageConfigStorage> configStorages);
}
